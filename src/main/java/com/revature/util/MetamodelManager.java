package com.revature.util;

import com.revature.reflectors.Metamodel;

import java.util.List;

public class MetamodelManager {

    private List<Metamodel<Class<?>>> metamodelList;

    MetamodelManager(){
        super();
    }

    public void addMetaModel(Metamodel metamodel){
        metamodelList.add(metamodel);
    }
}
