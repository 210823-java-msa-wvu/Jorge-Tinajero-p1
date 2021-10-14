function jsonDeCookifier(name){

     let cookies = document.cookie;

     if(cookies.length != 0){

         let cookieArray = cookies.split("=");
//            console.log(cookieArray);
         for(var i = 0; i < cookieArray.length - 1; i += 2){

         let cookieName = cookieArray[i];
//            console.log(cookieArray[i]);
             if(cookieName == name){
                 let uglyCookie = cookieArray[i + 1];
                 let lessUglyCookie = uglyCookie.replace(/\\/g,'');
                 let prettyCookie = lessUglyCookie.slice(1, lessUglyCookie.length - 13);
                console.log(prettyCookie + " pretty cookie")
                 let yoJson = JSON.parse(prettyCookie);

                 return yoJson;
             }
         }
     }else return null;
  }
let userInfo = jsonDeCookifier("username");
console.log(userInfo);

document.getElementById('employee').innerHTML=displayUser();

function displayUser(){
    //var Cookies = document.cookie.split(';');
    var upper = userInfo.userName[0].toUpperCase() + userInfo.userName.substring(1);
    var aString = ('Welcome ' + upper);
    return aString;
}

function displayMoney(){
    var moneyLeft = userInfo.money;
    document.getElementById('moneyLeft').innerHTML='Reimbursement money left: $' + moneyLeft
    //var moneyLeft = userInfo.money;
    //return ('Reimbursement money left: $' + moneyLeft);   
}


function logout(){
    var res = document.cookie;
    var multiple = res.split(";");
    if(multiple == null){

    }
    else{
     for(var i = 0; i < multiple.length; i++) {
           var key = multiple[i].split("=");
           document.cookie = key[0]+" =; expires = Thu, 01 Jan 1970 00:00:00 UTC";
           
       }
       alert("cookies have been cleared - no need to worry!");
    }
}

function logoutDiscreet(){
    var res = document.cookie;
    var multiple = res.split(";");
    if(multiple == null){

    }
    else{
     for(var i = 0; i < multiple.length; i++) {
           var key = multiple[i].split("=");
           document.cookie = key[0]+" =; expires = Thu, 01 Jan 1970 00:00:00 UTC";
           
       }
    }
}

// function login(){
//     let baseUrl = 'http://localhost:8080/p1/login';

//     let xhttp = new XMLHttpRequest();
//     // STEP 2: Set a callback function for the readystatechange event
//     //xhttp.onreadystatechange = receiveData;

//     // xhttp.send();

//     // if(xhttp.status == 403){
//     //     alert("You requested more than your current funds - contact your manager")
//     // }
//     xhttp.open("POST", baseUrl, true);
    
//     xhttp.onreadystatechange = function() {
//     if (this.readyState == 4 && this.status != 200) {
//       alert("Login credentials failed");
//         }
//     }
    
//     xhttp.setRequestHeader("Content-type", "Login-error");
//     xhttp.send("fname=Henry&lname=Ford");

// }