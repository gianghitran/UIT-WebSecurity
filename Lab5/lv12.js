Java.perform(() => {
    console.log("intenting");

    const Target = Java.use("java.util.Random");

    Target.nextInt.overload("int").implementation = function (limit) {
        return -150;
    };
});