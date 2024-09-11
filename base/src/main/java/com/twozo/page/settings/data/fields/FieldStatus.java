package com.twozo.page.settings.data.fields;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldStatus {

    private boolean isDraggable;
    private String fieldName;
    private String append;
    private String fieldType;
    private boolean isAddView;
    private boolean isRequired;
    private boolean isEditable;
    private boolean isHideable;
    private boolean isDeletable;
    private List<String> choices;

}
