/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.core.io.Resource;
import org.xml.sax.InputSource;

/**
 * @author KARTHIKEYAN N
 *
 */
public class SampleBean {
	
	private File file;
    private URL url;
    private Charset charset;
    private Class<?> clazz;
    private Class<?>[] clazzArray;
    private Currency currency;
    private InputStream inputStream;    
    private InputSource inputSource;
    private Locale locale;
    private Pattern pattern;
    private Properties properties;
    private Resource[] resources;
    private TimeZone timeZone;
    private UUID uuid;
    private byte[] bytes;
    private char[] charArray;
    private char character;
    private boolean bool;
    private double doubleValue;
    private String[] stringArray;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public Charset getCharset() {
		return charset;
	}
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Class<?>[] getClazzArray() {
		return clazzArray;
	}
	public void setClazzArray(Class<?>[] clazzArray) {
		this.clazzArray = clazzArray;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputSource getInputSource() {
		return inputSource;
	}
	public void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Pattern getPattern() {
		return pattern;
	}
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Resource[] getResources() {
		return resources;
	}
	public void setResources(Resource[] resources) {
		this.resources = resources;
	}
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public char[] getCharArray() {
		return charArray;
	}
	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}
	public char getCharacter() {
		return character;
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public String[] getStringArray() {
		return stringArray;
	}
	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

}
