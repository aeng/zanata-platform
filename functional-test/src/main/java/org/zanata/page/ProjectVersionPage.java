/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.zanata.page;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;

public class ProjectVersionPage extends AbstractPage
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ProjectVersionPage.class);

   @FindBy(className = "rich-table-row")
   private List<WebElement> localeTableRows;

   public ProjectVersionPage(final WebDriver driver)
   {
      super(driver);
   }

   public List<String> getTranslatableLocales()
   {
//      List<WebElement> tableRows = localeTable.findElements(By.className("rich-table-row"));
      Collection<String> rows = Collections2.transform(localeTableRows, new Function<WebElement, String>()
      {
         @Override
         public String apply(WebElement tr)
         {
            LOGGER.debug("table row: {}", tr.getText());
            List<WebElement> links = tr.findElements(By.tagName("a"));
            return getLocaleLinkText(links);
         }
      });

      return ImmutableList.copyOf(rows);
   }

   private static String getLocaleLinkText(List<WebElement> links)
   {
      return links.get(0).getText();
   }

   public WebTranPage translate(String locale)
   {

      for (WebElement tableRow : localeTableRows)
      {
         List<WebElement> links = tableRow.findElements(By.tagName("a"));
         Preconditions.checkState(links.size() == 4, "each translatable locale row should have 4 links");

         if (getLocaleLinkText(links).equals(locale))
         {
            WebElement translateLink = links.get(2);
            translateLink.click();
            return new WebTranPage(getDriver());
         }
      }
      throw new IllegalArgumentException("can not translate locale: " + locale);
   }
}
