package com.iLABCareers.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public abstract class PageObject {

	/**
	 * Retrieves the child element of {@link SearchContext} using ID selector.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param idSelector
	 *            The value of HTML element <b>id</b> attribute.
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementById(SearchContext searchContext,
			String idSelector) {
		return searchContext.findElement(By.id(idSelector));
	}

	/**
	 * Retrieves the child element of {@link SearchContext} using CSS selector.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param cssSelector
	 *            The css value of the HTML element.
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementByCSS(SearchContext searchContext,
			String cssSelector) {
		return searchContext.findElement(By.cssSelector(cssSelector));
	}

	/**
	 * Retrieves the child element of {@link SearchContext} using className
	 * selector.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param classSelector
	 *            The value of HTML element <b>class</b> attribute.
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementByClass(SearchContext searchContext,
			String className) {
		return searchContext.findElement(By.className(className));
	}

	/**
	 * Retrieves the child element of {@link SearchContext} using HTML tag.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param idSelector
	 *            The value of HTML <b>tag</b>
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementByTagName(
			SearchContext searchContext, String tagName) {
		return searchContext.findElement(By.tagName(tagName));
	}

	/**
	 * Retrieves the child element of {@link SearchContext} using element xpath.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param xPathSelector
	 *            The value of element <b>xpath</b>
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementByXpath(SearchContext searchContext,
			String xPathSelector) {
		return searchContext.findElement(By.xpath(xPathSelector));
	}
	
	/**
	 * Retrieves the child element of {@link SearchContext} using link text.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param linkText
	 *            Text shown on the link
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementByLinkText(SearchContext searchContext,
			String linkText) {
		return searchContext.findElement(By.linkText(linkText));
	}
	
	/**
	 * Retrieves the child element of {@link SearchContext} using css selector.
	 * 
	 * @param searchContext
	 *            Parent DOM object containing HTML elements.
	 * @param css
	 *            Html css selector.
	 * @return {@link WebElement}
	 */
	protected static WebElement getElementByCss(SearchContext searchContext,
			String css) {
		return searchContext.findElement(By.cssSelector(css));
	}
}
