package project;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped

@ManagedBean(name = "user")
public class calculator {

    private String name;
    private double num1;
    private double num2;
    private double result;
    private char ope;

    private String vis;

    public calculator() {
        this.name = "";
        this.vis = "";
    }

    public void adddot() {

        String all = this.name + ".";
        this.name = all;
    }

    public void clear() {
        this.name = "";
    }

    public void setsign() {
        double n = Double.parseDouble(name);
        n *= -1;
        name = String.valueOf(n);
    }

    public void setoff() {
        vis = "hidden";
    }

    public void setop(char ope) {
        num1 = Double.parseDouble(name);
        name = "";
        this.ope = ope;
    }

    public void setequal() {
        num2 = Double.parseDouble(name);
        switch (ope) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        name = String.format("%.0f",result);
    }

    public void add(String s) {

        String all = this.name + s;
        this.name = all;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

}
