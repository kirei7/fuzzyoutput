Points:
        a(x1)

b(x2)           c(x4)

        d(x3)

Rule
Rule is the entity that describes a single rule. It's POJO with fields:
float x1-x4 in range from 0 to 1 [0..1] where 0 is "no insects", 1 is "full of insects"
int x5 [1, 2] is wind direction where 1 is a-d direction(or vice versa), 2 is b-c direction(same here)


IMPORTANT
to obtain woring jar execute Maven goal:
mvn clean compile assembly:single