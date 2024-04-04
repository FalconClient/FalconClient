package test;

import ir.albino.client.features.ui.html.HTMLSerializable;
import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import lombok.ToString;


@ToString
public class ExampleHTML extends HTMLSerializable {
    public int age = 32;
    @HTMLIgnore
    public String testIgnore = "ignore";
    public String name = "Dan";
    public boolean married = true;
    public AnotherExample wife = new AnotherExample();
}
