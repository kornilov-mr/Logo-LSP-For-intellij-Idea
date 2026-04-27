package org.example.project;

public enum StandardLogoFunctions {
    // Control flow functions
    REPEAT(new FunctionDeclaration("repeat", 2, FunctionDeclaration.Kind.SPECIAL_FORM)),
    IF(new FunctionDeclaration("if", 2, FunctionDeclaration.Kind.SPECIAL_FORM)),
    IFELSE(new FunctionDeclaration("ifelse", 3, FunctionDeclaration.Kind.SPECIAL_FORM)),
    WHILE(new FunctionDeclaration("while", 2, FunctionDeclaration.Kind.SPECIAL_FORM)),
    FOREVER(new FunctionDeclaration("forever", 1, FunctionDeclaration.Kind.SPECIAL_FORM)),
    FOR(new FunctionDeclaration("for", 1, FunctionDeclaration.Kind.SPECIAL_FORM)),
    STOP(new FunctionDeclaration("stop", 0, FunctionDeclaration.Kind.BUILTIN)),
    OUTPUT(new FunctionDeclaration("output", 1, FunctionDeclaration.Kind.BUILTIN)),
    RUN(new FunctionDeclaration("run", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Turtle movement functions
    FORWARD(new FunctionDeclaration("forward", 1, FunctionDeclaration.Kind.BUILTIN)),
    FD(new FunctionDeclaration("fd", 1, FunctionDeclaration.Kind.BUILTIN)),
    BACK(new FunctionDeclaration("back", 1, FunctionDeclaration.Kind.BUILTIN)),
    BK(new FunctionDeclaration("bk", 1, FunctionDeclaration.Kind.BUILTIN)),
    LEFT(new FunctionDeclaration("left", 1, FunctionDeclaration.Kind.BUILTIN)),
    LT(new FunctionDeclaration("lt", 1, FunctionDeclaration.Kind.BUILTIN)),
    RIGHT(new FunctionDeclaration("right", 1, FunctionDeclaration.Kind.BUILTIN)),
    RT(new FunctionDeclaration("rt", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Turtle position functions
    SETXY(new FunctionDeclaration("setxy", 2, FunctionDeclaration.Kind.BUILTIN)),
    SETX(new FunctionDeclaration("setx", 1, FunctionDeclaration.Kind.BUILTIN)),
    SETY(new FunctionDeclaration("sety", 1, FunctionDeclaration.Kind.BUILTIN)),
    SETHEADING(new FunctionDeclaration("setheading", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Turtle state functions
    HOME(new FunctionDeclaration("home", 0, FunctionDeclaration.Kind.BUILTIN)),
    CLEARSCREEN(new FunctionDeclaration("clearscreen", 0, FunctionDeclaration.Kind.BUILTIN)),
    CS(new FunctionDeclaration("cs", 0, FunctionDeclaration.Kind.BUILTIN)),

    // Pen functions
    PENUP(new FunctionDeclaration("penup", 0, FunctionDeclaration.Kind.BUILTIN)),
    PU(new FunctionDeclaration("pu", 0, FunctionDeclaration.Kind.BUILTIN)),
    PENDOWN(new FunctionDeclaration("pendown", 0, FunctionDeclaration.Kind.BUILTIN)),
    PD(new FunctionDeclaration("pd", 0, FunctionDeclaration.Kind.BUILTIN)),

    // Turtle visibility functions
    HIDETURTLE(new FunctionDeclaration("hideturtle", 0, FunctionDeclaration.Kind.BUILTIN)),
    HT(new FunctionDeclaration("ht", 0, FunctionDeclaration.Kind.BUILTIN)),
    SHOWTURTLE(new FunctionDeclaration("showturtle", 0, FunctionDeclaration.Kind.BUILTIN)),
    ST(new FunctionDeclaration("st", 0, FunctionDeclaration.Kind.BUILTIN)),

    // Pen properties functions
    SETPENSIZE(new FunctionDeclaration("setpensize", 1, FunctionDeclaration.Kind.BUILTIN)),
    SETPENCOLOR(new FunctionDeclaration("setpencolor", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Arithmetic functions
    SUM(new FunctionDeclaration("sum", 2, FunctionDeclaration.Kind.BUILTIN)),
    DIFFERENCE(new FunctionDeclaration("difference", 2, FunctionDeclaration.Kind.BUILTIN)),
    PRODUCT(new FunctionDeclaration("product", 2, FunctionDeclaration.Kind.BUILTIN)),
    QUOTIENT(new FunctionDeclaration("quotient", 2, FunctionDeclaration.Kind.BUILTIN)),
    REMAINDER(new FunctionDeclaration("remainder", 2, FunctionDeclaration.Kind.BUILTIN)),

    // Math functions
    SQRT(new FunctionDeclaration("sqrt", 1, FunctionDeclaration.Kind.BUILTIN)),
    POWER(new FunctionDeclaration("power", 2, FunctionDeclaration.Kind.BUILTIN)),
    ABS(new FunctionDeclaration("abs", 1, FunctionDeclaration.Kind.BUILTIN)),
    ROUND(new FunctionDeclaration("round", 1, FunctionDeclaration.Kind.BUILTIN)),
    INT(new FunctionDeclaration("int", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Trigonometric functions
    SIN(new FunctionDeclaration("sin", 1, FunctionDeclaration.Kind.BUILTIN)),
    COS(new FunctionDeclaration("cos", 1, FunctionDeclaration.Kind.BUILTIN)),
    TAN(new FunctionDeclaration("tan", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Random function
    RANDOM(new FunctionDeclaration("random", 1, FunctionDeclaration.Kind.BUILTIN)),

    // List functions
    LIST(new FunctionDeclaration("list", -1, FunctionDeclaration.Kind.BUILTIN)),
    SENTENCE(new FunctionDeclaration("sentence", -1, FunctionDeclaration.Kind.BUILTIN)),
    FIRST(new FunctionDeclaration("first", 1, FunctionDeclaration.Kind.BUILTIN)),
    LAST(new FunctionDeclaration("last", 1, FunctionDeclaration.Kind.BUILTIN)),
    BUTFIRST(new FunctionDeclaration("butfirst", 1, FunctionDeclaration.Kind.BUILTIN)),
    BUTLAST(new FunctionDeclaration("butlast", 1, FunctionDeclaration.Kind.BUILTIN)),
    ITEM(new FunctionDeclaration("item", 2, FunctionDeclaration.Kind.BUILTIN)),
    COUNT(new FunctionDeclaration("count", 1, FunctionDeclaration.Kind.BUILTIN)),
    FPUT(new FunctionDeclaration("fput", 2, FunctionDeclaration.Kind.BUILTIN)),
    LPUT(new FunctionDeclaration("lput", 2, FunctionDeclaration.Kind.BUILTIN)),

    // String functions
    WORD(new FunctionDeclaration("word", -1, FunctionDeclaration.Kind.BUILTIN)),
    UPPERCASE(new FunctionDeclaration("uppercase", 1, FunctionDeclaration.Kind.BUILTIN)),
    LOWERCASE(new FunctionDeclaration("lowercase", 1, FunctionDeclaration.Kind.BUILTIN)),
    CHAR(new FunctionDeclaration("char", 1, FunctionDeclaration.Kind.BUILTIN)),
    ASCII(new FunctionDeclaration("ascii", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Variable functions
    MAKE(new FunctionDeclaration("make", 2, FunctionDeclaration.Kind.SPECIAL_FORM)),
    NAME_QUERY(new FunctionDeclaration("name?", 1, FunctionDeclaration.Kind.BUILTIN)),
    THING(new FunctionDeclaration("thing", 1, FunctionDeclaration.Kind.BUILTIN)),
    LOCAL(new FunctionDeclaration("local", 1, FunctionDeclaration.Kind.BUILTIN)),
    ERASE(new FunctionDeclaration("erase", 1, FunctionDeclaration.Kind.BUILTIN)),

    // I/O functions
    PRINT(new FunctionDeclaration("print", 1, FunctionDeclaration.Kind.BUILTIN)),
    SHOW(new FunctionDeclaration("show", 1, FunctionDeclaration.Kind.BUILTIN)),
    TYPE(new FunctionDeclaration("type", 1, FunctionDeclaration.Kind.BUILTIN)),
    READWORD(new FunctionDeclaration("readword", 0, FunctionDeclaration.Kind.BUILTIN)),
    READLIST(new FunctionDeclaration("readlist", 0, FunctionDeclaration.Kind.BUILTIN)),

    // Logical functions
    AND(new FunctionDeclaration("and", 2, FunctionDeclaration.Kind.BUILTIN)),
    OR(new FunctionDeclaration("or", 2, FunctionDeclaration.Kind.BUILTIN)),
    NOT(new FunctionDeclaration("not", 1, FunctionDeclaration.Kind.BUILTIN)),

    // Comparison functions
    EQUAL_QUERY(new FunctionDeclaration("equal?", 2, FunctionDeclaration.Kind.BUILTIN)),
    GREATER_QUERY(new FunctionDeclaration("greater?", 2, FunctionDeclaration.Kind.BUILTIN)),
    LESS_QUERY(new FunctionDeclaration("less?", 2, FunctionDeclaration.Kind.BUILTIN)),

    // Meta functions
    APPLY(new FunctionDeclaration("apply", 2, FunctionDeclaration.Kind.BUILTIN)),
    INVOKE(new FunctionDeclaration("invoke", -1, FunctionDeclaration.Kind.BUILTIN));

    public final FunctionDeclaration declaration;

    StandardLogoFunctions(FunctionDeclaration declaration) {
        this.declaration = declaration;
    }
}