package test;

import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import lombok.ToString;


@ToString
public class ExampleHTML implements HTMLSerializable {
    public int age = 32;
    @HTMLIgnore
    public String testIgnore = "ignore";
    public String name = "Dan";
    public boolean married = true;
    public AnotherExample wife = new AnotherExample();
}
