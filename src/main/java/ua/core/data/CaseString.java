package ua.core.data;

import java.util.EnumSet;
import java.util.List;

import ua.core.enums.Case;
import ua.core.utils.StringUtils;

/**
 * Converts a string into different cases and forms.
 * 
 * @author Tadhg
 *
 */
public class CaseString {

    private final String value;
    private final List<String> words;

    public CaseString (String value) {

        if (value == null) {
            throw new NullPointerException ("value - null not allowed.");
        }

        this.value = value;
        this.words = StringUtils.toWords (value);
    }

    public String getValue() {
        return value;
    }

    public List<String> getWords() {
        return words;
    }

    public String camelCase() {
        return StringUtils.to1stLowerCase (value);
    }

    public String camelHyphenCase() {
        return StringUtils.toCase (words, EnumSet.of (Case.CAMEL, Case.HYPHEN));
    }

    public String camelSnakeCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.CAMEL, Case.SNAKE));
    }

    public String lowerCase() {
        return StringUtils.toLowerCase (value);
    }

    public String lowerHyphenCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.LOWER, Case.HYPHEN));
    }

    public String lowerSnakeCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.LOWER, Case.SNAKE));
    }

    public String titleCase() {
        return StringUtils.to1stUpperCase (value);
    }

    public String titleHyphenCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.TITLE, Case.HYPHEN));
    }

    public String titleSnakeCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.TITLE, Case.SNAKE));
    }

    public String upperCase() {
        return StringUtils.toUpperCase (value);
    }

    public String upperHyphenCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.UPPER, Case.HYPHEN));
    }

    public String upperSnakeCase() {
    		return StringUtils.toCase (words, EnumSet.of (Case.UPPER, Case.SNAKE));
    }
    
    public String value() {
    		return value;
    }

    @Override
    public String toString() {
        return "TextForm [" +
            "value=" + ((value != null) ? "\"" + value + "\"" : "null") +
            "]";
    }
    
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Do Not Overwrite With Generated Code...                                                                       //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaseString other = (CaseString) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
    
}