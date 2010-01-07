/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


jQuery(document).ready(function()
{
    jQuery(".defaultText").focus(function(srcc)
    {
        if (jQuery(this).val() == jQuery(this)[0].title)
        {
            jQuery(this).removeClass("defaultTextActive");
            jQuery(this).val("");
        }
    });

    jQuery(".defaultText").blur(function()
    {
        if (jQuery(this).val() == "")
        {
            jQuery(this).addClass("defaultTextActive");
            jQuery(this).val(jQuery(this)[0].title);
        }
    });

    jQuery(".defaultText").blur();
});
