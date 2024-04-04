package test;

import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import lombok.ToString;

@ToString
public class AnotherExample implements HTMLSerializable {
    public String name = "Mary";
    public int age = 25;
    public String job = "Doctor";

}
