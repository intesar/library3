jq(document).ready(function() {
    var cust = null;
    function writePerson() {
        dwr.util.useLoadingMessage("Please Wait Loading....");
        var customer = null;
        if ( cust == null ) {
            customer = {
                id:null,
                name:null,
                img:null,
                email:null,
                homePhone:null,
                mobilePhone:null,
                otherPhone:null,
                street:null,
                city:null,
                zipcode:null,
                state:null,
                country:null,
                passportNo:null,
                voterId:null,
                collegeName:null,
                rationCardNo:null,
                panCardNo:null,
                dob:null,
                gender:null,
                comments:null
            };

        } else {
            customer = cust;
        }

        dwr.util.getValues(customer);

        var flag1 = customer.img.value.toString().toLowerCase().search ('jpg') ;
        var flag2 = customer.img.value.toString().toLowerCase().search ('bmp') ;
        var flag3 = customer.img.value.toString().toLowerCase().search ('png') ;

        if ( flag1 > 0 || flag2 > 0 || flag3 > 0 || customer.img.value == null || customer.img.value == "") {

            if ( validateEmail(customer.email, true, true) ) {

                if ( /*customer.img !="" && */ customer.name != "" && customer.street !=""  && customer.city !="" && customer.zipcode !="" &&  customer.state != "" && customer.country!="")
                {
                    AjaxWorkService.createCustomer(customer, reply1);
                }
                else {
                    alert ( " * Marked Fields are required ! ");
                }

            }

        } else {
            alert ( ' Image type can only be jpg, bmp, png');
        }
    //dwr.engine.endBatch();
    }

    var reply1 = function (data) {
        clearMessages();
        if ( data == "Customer Created Successfully!" ) {
            writeMessage ("successReply", "Created/Updated Profile at " + new Date().toLocaleString());
        } else {
            writeMessage ("failureReply", "Please try again with different values");
        }
    }


    function execute() {
        dwr.util.useLoadingMessage("Please Wait");
        AjaxAdminService.getSystemLease(document.getElementById("DPC_startDate_YYYY-MM-DD").value,
            reply1 );
    }



    function clearPerson() {
        dwr.util.setValues({
            id:null,
            img:null,
            name:null,
            email:null,
            homePhone:null,
            mobilePhone:null,
            otherPhone:null,
            street:null,
            city:null,
            zipcode:null,
            state:null,
            country:null,
            passportNo:null,
            voterIdCardNo:null,
            collegeName:null,
            rationCardNo:null,
            panCardNo:null,
            dob:null,
            gender:null,
            comments:null
        });
        cust = null;
    }

    jq("#searchBtn").click(function() {
        search();
    })

    function search() {
        dwr.util.useLoadingMessage("Please Wait");
        AjaxWorkService.getUserWithPic(dwr.util.getValue("key"), reply2);
    }

    var reply2 = function(customer) {
        cust = customer;
        dwr.util.setValue("image", null);
        clearMessages();
        if ( customer.id == null ) {
            writeMessage ("failureReply", "No Match for the Given Email, Please create User Profile " );
        } else {
            writeMessage ("successReply", "Found " + customer.name );
            dwr.util.setValues(customer);
        }
    }

    jq("form.jqtransform").jqTransform();
})

