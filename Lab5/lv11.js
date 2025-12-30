Java.perform(() => {
    console.log("intenting");

    const target = Java.use("android.content.Intent");

    target.putExtra.overload('java.lang.String', 'java.lang.String').implementation = function(name, data) {
        console.log(`Flag: EVABS{${data}}`);
        
        return this.putExtra(name, data);
    };
});