package com.youngculture.webshop_onboarding.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FormatCategoryTag extends SimpleTagSupport {

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String formattedCategory = category.toLowerCase().substring(0,1).toUpperCase()
                + category.toLowerCase().substring(1);
        out.print(formattedCategory);
    }

}
