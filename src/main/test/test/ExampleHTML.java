package test;

import ir.albino.client.features.ui.html.HTMLSerializable;
import lombok.ToString;


@ToString
public class ExampleHTML extends HTMLSerializable {
    public int age = 32;
    public String name = "Dan";
    public boolean married = true;
    public AnotherExample wife = new AnotherExample();
}
