Java.perform
(
    function()
    {
        console.log("Inside the hook_script");
        var revo = Java.use("com.revo.evabs.Welcome");
        revo.onClick.implementation = function(v)
        {
            console.log ("overwite ");
            var flag = this.stringFromJNTI();
            console.log("Flag returned: EVABS{" + flag +"}");
            this.onClick(v);
        };
    }
);