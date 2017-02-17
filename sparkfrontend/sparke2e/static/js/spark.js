var i=1;
var finalhtml = [];
var html1, html2, html3;
var templatebottom = '<!DOCTYPE html> <html> <head> <title>bottom text</title> <style> #bottomdiv {margin: auto;position: relative; width: 480px; height: 320; } #bottomimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #bottomtext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute; bottom: 21px; text-shadow: 2px 2px 4px #000000; } </style>  <body> <div id="bottomdiv"> <img class="ad" id="bottomimg" src="$image"> <img class="logo" src="$iconurl"> <p id="bottomtext">$text</p> </div> </body> </html>';
var templatetop = '<!DOCTYPE html> <html> <head> <title>top text</title> <style> #topdiv{margin: auto;position: relative; width: 480px; height: 320; } #topimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #toptext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute; top: 0px; text-shadow: 2px 2px 4px #000000; } </style>  <body> <div id="topdiv"> <img class="ad" id="topimg" src="$image"> <img class="logo" src="$iconurl"> <p id="toptext">$text</p> </div> </body> </html>';
var templatecenter = '<!DOCTYPE html> <html> <head> <title>center text</title> <style> #centerdiv {margin: auto;position: relative; width: 480px; height: 320; } #centerimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; opacity: 0.7; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #centertext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute; bottom: 160px; text-shadow: 2px 2px 4px #000000; } </style>  <body> <div id="centerdiv"> <img class="ad" id="centerimg" src="$image"> <img class="logo" src="$iconurl"> <p id="centertext">$text</p> </div> </body> </html>';
var templatecenterhack = '<!DOCTYPE html> <html> <head> <title>center text</title> <style> #centerdiv {margin: auto;position: relative; width: 480px; height: 320; } #centerhackimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; opacity: 0.7; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #centertext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute;margin-bottom: 0px; bottom: 160px; text-shadow: 2px 2px 4px #000000; } </style> <body> <div id="centerdiv"> <img class="ad" id="centerhackimg" src="$image"> <img class="logo" src="$iconurl"> <p id="centertext" style="height: 15px;">$text</p> </div> </body> </html>';

$(document).ready(function(){
    $("#add").click(function () {
        i++;
        $(".inputassets").append('<div id="assetborder'+i+'" class="asset"><input type="text"  name="field'+i+'a" id="image'+i+'" placeholder="Image URL '+i+'"><input type="text" name="field'+i+'b" id="text'+i+'" placeholder="Text"><input type="text" name="field'+i+'c" id="weight'+i+'" placeholder="Weight (in %)"><div id="template'+i+'"></div><button class="buttonx" onclick="previewTemplatesForOneAsset(this.id)" id="'+i+'" type="submit" ><span><y id="btnlbla">Pick Template</y></span></button></div>');
        return false;
    });

    $("#remove").click(function () {
        document.getElementById('image'+i).remove()
        document.getElementById('text'+i).remove();
        document.getElementById('weight'+i).remove();
        document.getElementById('template'+i).remove();
        document.getElementById('assetborder'+i).remove();
        document.getElementById(i).remove();
        i--;
        return false;
    });
});

function showLoading(){
    document.getElementById("loading").style.display = "inline";
    $("#allcontainer").css("opacity","0.3");
    $("#loading").fadeIn(300);
}

function hideLoading(){
    document.getElementById("loading").style.display = "none";
    $("#allcontainer").css("opacity","1");
    $("#loading").fadeOut(300);
}

function previewTemplatesForOneAsset(id){
    if(document.getElementById('id'+i) != null){
        document.getElementById('id'+i).remove();
    }
    var logourl = document.getElementById("iconurl").value;
    var imageurl = document.getElementById("image"+id).value;
    var text = document.getElementById("text"+id).value;
    var length = document.getElementById("image"+id).value.length;
    var format = document.getElementById("image"+id).value.substring(document.getElementById("image"+id).value.length-3,document.getElementById("image"+id).value.length);
    var formata = document.getElementById("image"+id).value.substring(document.getElementById("image"+id).value.length-4,document.getElementById("image"+id).value.length);

    var templatebottom = '<!DOCTYPE html> <html> <head> <title>bottom text</title> <style> #bottomdiv {margin: auto;position: relative; width: 480px; height: 320; } #bottomimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; } img.logo{margin: auto; position: relative; width: 480px; height: 320;} #bottomtext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute; bottom: 21px; text-shadow: 2px 2px 4px #000000; } </style>  <body> <div id="bottomdiv"> <img class="ad" id="bottomimg" src="$image"> <img class="logo" src="$iconurl"> <p id="bottomtext">$text</p> </div> </body> </html>';
    var templatetop = '<!DOCTYPE html> <html> <head> <title>top text</title> <style> #topdiv{margin: auto;position: relative; width: 480px; height: 320; } #topimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #toptext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute; top: 0px; text-shadow: 2px 2px 4px #000000; } </style>  <body> <div id="topdiv"> <img class="ad" id="topimg" src="$image"> <img class="logo" src="$iconurl"> <p id="toptext">$text</p> </div> </body> </html>';
    var templatecenter = '<!DOCTYPE html> <html> <head> <title>center text</title> <style> #centerdiv {margin: auto;position: relative; width: 480px; height: 320; } #centerimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; opacity: 0.7; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #centertext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute; bottom: 160px; text-shadow: 2px 2px 4px #000000; } </style>  <body> <div id="centerdiv"> <img class="ad" id="centerimg" src="$image"> <img class="logo" src="$iconurl"> <p id="centertext">$text</p> </div> </body> </html>';
    var templatecenterhack = '<!DOCTYPE html> <html> <head> <title>center text</title> <style> #centerdiv {margin: auto;position: relative; width: 480px; height: 320; } #centerhackimg{width: 480px; height: 320px; border-radius: 3%; position: relative; top: 0px; opacity: 0.7; } img.logo{bottom: 37px; height: 32px; left: 450px; position: relative; width: 30px; border-radius: 5px; padding: inherit;} #centertext{width: 480px; text-align: center; color: white; font-size: 25px; position: absolute;margin-bottom: 0px; bottom: 160px; text-shadow: 2px 2px 4px #000000; } </style> <body> <div id="centerdiv"> <img class="ad" id="centerhackimg" src="$image"> <img class="logo" src="$iconurl"> <p id="centertext" style="height: 15px;">$text</p> </div> </body> </html>';
    alert(text);
    if(imageurl == "" || imageurl == null){
        alert("Please input Image Url.")
    }else if(text == "" || text == null){
        alert("Please input text.")
    }else if(!(format == "png" || format == "jpg" || formata == "jpeg" || format == "gif" ||
        format == "svg" || formata == "apng" || format == "bmp")){
        alert("Please check Image URL. Supported formats - PNG, JPG, JPEG, GIF, SVG, APNG, BMP");
    }else{
        html1 = templatebottom.replace("$text",text).replace("$image",imageurl).replace("$iconurl",logourl);
        html2 = templatetop.replace("$text",text).replace("$image",imageurl).replace("$iconurl",logourl);
        html3 = templatecenter.replace("$text",text).replace("$image",imageurl).replace("$iconurl",logourl);
        html3hack = templatecenterhack.replace("$text",text).replace("$image",imageurl).replace("$iconurl",logourl);
        var modal='<div id="id'+id+'" class="modal"><div class="modal-content1 animate"><button type="button" style="border-radius:20px" onclick="closeModal('+id+')" class="cancelbtn">x</button><div class="imgcontainer"><y id="feedthanksa"><h4>Click on the template to select </h4></y></div><div class="containery" style="padding:10px;"> </div><x id="Bottom" style="margin: auto;width:100%;" onclick="pickTemplate(this.id,'+id+')">'+html1+'</x><br><br><x id="Top" style="margin: auto;" onclick="pickTemplate(this.id,'+id+')">'+html2+'</x><br><br> <x id="Center" style="margin: auto;" onclick="pickTemplate(this.id,'+id+')">'+html3hack+'</x><br><br>  </div>';
        $("body").append(modal);
        document.getElementById('id'+id).style.display='block';
    }
}

function pickTemplate(templateid,id){
    if(document.getElementById('templateinput'+id) != null){
        document.getElementById('templateinput'+id).remove();
    }
    var template='<input type="text" name="field'+id+'c" id="templateinput'+id+'" value="Template Picked : '+templateid+'">';
    $("#template"+id).append(template);
    switch(templateid) {
        case "Bottom":
            finalhtml[id] = html1;
            break;
        case "Top"   :
            finalhtml[id] = html2;
            break;
        case "Center":
            finalhtml[id] = html3;
            break;
    }
    document.getElementById('id'+id).style.display='none';
}

function closeModal(id){
    document.getElementById('id'+id).style.display='none';
}

function createVideo(){
    var campaignname = document.getElementById("campaignname").value;
    var audiourl = document.getElementById("audio").value;
    var iconurl = document.getElementById("iconurl").value;
    var weight = [];
    var numberOfImages = i;
    var flag = 0;
    var fixedWeight = (1/numberOfImages)*100;
    var total=0;
    var audiourllength = audiourl.length;
    var audioformat = audiourl.substring(audiourllength-3,audiourllength);

    if(campaignname == null || campaignname == ""   ){
        alert("Please provide the Campaign name for which the Video is to be created. It helps us in logging.")
        flag +=1 ;
    }else if(audiourl == null || audiourl == ""){
        alert("Please provide the Audio link. Its a mandatory field.")
        flag +=1 ;
    }else if(audioformat != "mp3"){
        alert("Please provide a .mp3 audio link as Audio input.");
        flag+=1;
    }
    for(k=1;k<=numberOfImages;k++){
        weight[k] = document.getElementById("weight"+k).value;;
        total += parseInt(weight[k]);
        if(finalhtml[k] == null){
            alert("Please select Template for Image "+k);
            flag += 1;
        }
        if(weight[k] == null){
            alert("Please enter weight (in %) for Image "+k);
            flag += 1;
        }
    }
    var resultJsonObject ={};
    var assetJsonObject = {};
    var assetsJsonArray = [];
    resultJsonObject.assetsJsonArray = assetsJsonArray;
    if(flag == 0){
        if(total != 100){
            alert("Total weight has to be 100%. Please update the weight inputs.")
        }else{
            for(z=1;z<=numberOfImages;z++){
                assetJsonObject = {'html':finalhtml[z], 'weight':weight[z]};
                resultJsonObject.assetsJsonArray.push(assetJsonObject);
            }
            resultJsonObject.campaignname = campaignname;
            resultJsonObject.audiourl = audiourl;
            resultJsonObject.numberofimages = numberOfImages;
            strigifiedInput = JSON.stringify(resultJsonObject);
            console.log(strigifiedInput);

            var data = {};
            data.info = strigifiedInput;
            generateVideo("/sparke2e/generatevideo/",data);
//            showLoading();
        }

    }
}

function generateVideo(url,data){
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        async:false,
        crossDomain : true,
        dataType : "text",
        success : function(data) {
            alert(data)
            var status=data.response.status;
            if(data == "success"){
//                  hideLoading();
//                url = data.response.validator
                alert("Thanks for your feedback1111.");
            }else if (status == 500){
//                hideLoading();
                alert("Some error occurred. Please try again later1112.");
            }
        },
        error: function(data) {
//            hideLoading();
            window.alert("Thanks for your feedback312123.");
        }
    });
}

//    if(!(url1substr == "png" || url1substr == "jpg" || url1asubstr == "jpeg" || url1substr == "gif" ||
//        url1substr == "svg" || url1asubstr == "apng" || url1substr == "bmp")){
//        alert("Please check Image URL 1. Supported formats - PNG, JPG, JPEG, GIF, SVG, APNG, BMP");
//    }else if(!(url2substr == "png" || url2substr == "jpg" || url2asubstr == "jpeg" || url2substr == "gif" ||
//        url2substr == "svg" || url2asubstr == "apng" || url2substr == "bmp")){
//        alert("Please check Image URL 2. Supported formats - PNG, JPG, JPEG, GIF, SVG, APNG, BMP");
//    }else if(!(url3substr == "png" || url3substr == "jpg" || url3asubstr == "jpeg" || url3substr == "gif" ||
//        url3substr == "svg" || url3asubstr == "apng" || url3substr == "bmp")){
//        alert("Please check Image URL 2. Supported formats - PNG, JPG, JPEG, GIF, SVG, APNG, BMP");
//    }else if(!(url4substr == "png" || url4substr == "jpg" || url4asubstr == "jpeg" || url4substr == "gif" ||
//        url4substr == "svg" || url4asubstr == "apng" || url4substr == "bmp")){
//        alert("Please check Image URL 3. Supported formats - PNG, JPG, JPEG, GIF, SVG, APNG, BMP");
//    }else{
//        document.getElementById('id01').style.display='block';
//    }