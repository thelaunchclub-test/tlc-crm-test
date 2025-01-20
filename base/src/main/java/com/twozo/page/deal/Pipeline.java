package com.twozo.page.deal;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Pipeline {
    public String pipelineName;
    public boolean isDefault;
    public String dealRotAfter;
    public Map<String,String> stages;
}
