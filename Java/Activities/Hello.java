import java.util.HashSet;
import java.util.Set;

public class Hello {
    public static void main (String args[])
    {
Set<String> names = new HashSet<>();
names.add("Tom");
names.add("Mary");
names.add("Peter");
names.add("Alice");
 
System.out.println("The set has " + names.size() + " elements");
}
}