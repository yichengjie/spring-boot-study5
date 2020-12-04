package com.yicj.study.transaction;

import lombok.Setter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditor;
import java.util.Date;

public class DatePropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Setter
    private PropertyEditor propertyEditor ;

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Date.class, propertyEditor);
    }
}
