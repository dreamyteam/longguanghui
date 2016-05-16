package com.dreamy.lgh.handlers;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors
     * (org.springframework.beans.PropertyEditorRegistry)
     */
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //registry.registerCustomEditor(Date.class, new DateTypeEditor());
    }

}
