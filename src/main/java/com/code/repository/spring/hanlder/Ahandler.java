package com.code.repository.spring.hanlder;

import com.code.repository.spring.Handler;
import com.code.repository.spring.IHandler;

/**
 * @Author zhaoyuan.lizy on 2019/1/14
 **/

@Handler(value="Ahandler",aliases={"Ahandler alias1","Ahandler alias2"})
public class Ahandler implements IHandler {

    public String invoke(){
        return "Ahandler is invoke";
    }
}
