function supervisorRequests(){

    let baseUrl = 'http://localhost:8080/p1/directsupervisor?';

    console.log('Button clicked');

    let xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', baseUrl, true); // true is the default argument - meaning that this is async

    // STEP 4: Send the request
    xhttp.send(); // for get requests, the send function does not have any arguments

    function receiveData() {
        
        let dataSection = document.getElementById('show');
        dataSection.innerHTML = '';
        
        let tagh3 = document.createElement('h3');
        tagh3.innerHTML = "These are your reimbursement requests: "
        dataSection.appendChild(tagh3); 
        // Check if the readyState is 'DONE' aka '4' and if the HTTP Status is 'ok' 200
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let r = xhttp.responseText;
            // console.log(r);

            r = JSON.parse(r);
            console.log(r);
            // r2 = JSON.parse(r2);
            // console.log(r);
            //might require for loop
            for(var i=0; i<r.length; i++)
            populateData(r,i);

        }

    }
}


function populateData(res, index) {

    // This is where we will do our DOM manipulation
    let dataSection = document.getElementById('show');
    // Create a new element
    let tagBr = document.createElement('br');
    

    // Append this new element to the section element (adding it as a child node in the DOM)
    // dataSection.appendChild(tagh3);

    // Now we'll use the same process for the Abilities
    let resArray = res[index];
    console.log(resArray);
    
    let tagTable = document.createElement('TABLE');
    tagTable.border = '2';

    let table2 = document.createElement('TABLE');
    let tableBody2 = document.createElement('TBODY');

    table2.appendChild(tableBody2);    

    let tableBody = document.createElement('TBODY');
    tagTable.appendChild(tableBody);
    let tr = document.createElement('TR');
    let th = document.createElement('TH');
    let tc = document.createElement('TC');
    let tr2 = document.createElement('TR');
    // tr.innerHTML = ("id ");
    tr.appendChild(th);
    tr2.appendChild(tc);
    tableBody.appendChild(tr);  
    tableBody.appendChild(tr2);

    if(resArray.directSuper){
        var dS = "approved";
    }
    else if(!resArray.directSuper){
        var dS = "denied";
    }
    else if(resArray.directSuper == null){
        var dS = "pending";
    }
    if(resArray.departmentHead){
        var dH = "approved";
    }
    else if(!resArray.departmentHead){
        var dH = "denied";
    }
    else{
        var dH = "pending";
    }
    if(resArray.benCo){
        var bC = "approved";
    }
    else if(!resArray.benCo){
        var bC = "denied";
    }
    else{
        var bC = "pending";
    }

    let infoArray = [resArray.employeeUser, '|','$',resArray.reimburse, '|',resArray.courseStartdate, '|', resArray.place,'|',resArray.description,'|',resArray.justification,'|', resArray.grading,'|', resArray.pass,'|', dS,'|', dH,'|', bC,'|', resArray.approved,'|', resArray.urgent];
    let headerArray = ['Employee|','Reimbursement|', 'Course Date|', 'Place|', 'Description|', 'Justification|', 'Grading|','Passed|', 'DSApp|','DHApp|','BencoApp|','Approved|', 'Urgent'];
    // let infoArray2 = [];
    // let 2ndheader = ['Supervisor Approval', 'Department Head Approval', 'Benefactor Approval'];
    for(var i=0; i<headerArray.length; i++){
        let td = document.createElement('TD');
        td.innerHTML = headerArray[i];
        th.appendChild(td);
    }
    for(var j=0; j< infoArray.length; j++){
        let tInfo= document.createElement('TD');
        tInfo.innerHTML = infoArray[j];
        tc.appendChild(tInfo);
        
    }
    let pass = document.createElement('form');
        pass.setAttribute('method' , 'POST');
        pass.setAttribute('action', 'directsupervisor');
        pass.setAttribute('id', 'approval');
        pass.innerHTML = (`
            <label for="approval"> Approve/Deny Request: </label>
            <select name="approval" id="approval">
            <option value = 0 > Select Choice</option>
            <option value = "approve" >Approve</option>
            <option value = "deny">Deny</option>
            </select><br><h6>(If denied add a reason)<h6>
            <textarea name="reason" id="reason" maxlength="150" placeholder="150 Max Characters"></textarea>
            <label for="additional"> Request More Info: </label>
            <select name="additional" id="additional">
            <option value = 0 > Select Choice</option>
            <option value = "true" >Request</option>
            </select>` );

    // if(document.getElementById("approval").value == "deny"){
    //     let reasonTag = document.createElement('textarea');
    //     reasonTag.setAttribute("id", 'reason');
    //     reasonTag.setAttribute("name", 'reason');
    //     reasonTag.setAttribute("placeholder", "Reason for denying?");
    //     pass.appendChild(reasonTag);
    // }
    
    let label = document.createElement('label');
    label.setAttribute('for', 'approval_id');
    label.innerHTML = "Approval Id";
    pass.appendChild(label);

    let inputTag = document.createElement('select');
    inputTag.setAttribute('id', 'approvalId');
    inputTag.setAttribute('name', 'approvalId');
    pass.appendChild(inputTag);

    let option = document.createElement('option');
    option.setAttribute('value', resArray.id);
    option.innerHTML = resArray.id;
    inputTag.appendChild(option);
    pass.appendChild(document.createElement("br"));

    let buttonTag = document.createElement('button');
    buttonTag.setAttribute('type', 'submit');
    buttonTag.setAttribute('value', 'Submit');
    buttonTag.setAttribute('onclick', 'confirmation()');
    buttonTag.setAttribute('class', "btn btn-sm btn-primary")
    buttonTag.innerHTML = 'Submit';
    //pass.appendChild(inputTag);
    pass.appendChild(buttonTag);
    
    tagTable.appendChild(pass);
    dataSection.appendChild(tagTable);
    dataSection.appendChild(tagBr);
    dataSection.appendChild(tagBr);
    dataSection.appendChild(pass);
    dataSection.appendChild(tagBr);
}
function confirmation(){
    alert("Approved/Denied submission");
}
function headRequests(){
    let baseUrl = 'http://localhost:8080/p1/directsupervisor?';

    // let reimburse = jsonDeCookieReimbursement("reimbursement");
    // console.log(reimburse + " reimbursements");

    //     var Cookies = document.cookie.split(';');
    //     var aString = ('these are the results = ' + reimburse.pass + reimburse.approved);
    //     document.getElementById('show').innerHTML = aString;
    console.log('Button clicked');

    
    let xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', baseUrl, true); // true is the default argument - meaning that this is async

    // STEP 4: Send the request
    xhttp.send(); // for get requests, the send function does not have any arguments

    function receiveData() {
        
        let dataSection = document.getElementById('show');
        dataSection.innerHTML = '';
        
        let tagh3 = document.createElement('h3');
        tagh3.innerHTML = "These are your reimbursement requests: "
        dataSection.appendChild(tagh3); 
        // Check if the readyState is 'DONE' aka '4' and if the HTTP Status is 'ok' 200
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let r = xhttp.responseText;
            // console.log(r);

            r = JSON.parse(r);
            // r2 = JSON.parse(r2);
            // console.log(r);
            //might require for loop
            for(var i=0; i<r.length; i++)
            headpopulateData(r,i);

        }

    }
}


function headpopulateData(res, index) {

    // This is where we will do our DOM manipulation
    let dataSection = document.getElementById('show');
    // Create a new element
    let tagBr = document.createElement('br');
    

    // Append this new element to the section element (adding it as a child node in the DOM)
    // dataSection.appendChild(tagh3);

    // Now we'll use the same process for the Abilities
    let resArray = res[index];
    console.log(resArray);
    
    // console.log(spritesObject);
    let tagTable = document.createElement('TABLE');
    tagTable.border = '2';

    // let table2 = document.createElement('TABLE');
    // let tableBody2 = document.createElement('TBODY');

    // table2.appendChild(tableBody2);    

    let tableBody = document.createElement('TBODY');
    tagTable.appendChild(tableBody);
    let tr = document.createElement('TR');
    let th = document.createElement('TH');
    let tc = document.createElement('TC');
    let tr2 = document.createElement('TR');
    // tr.innerHTML = ("id ");
    tr.appendChild(th);
    tr2.appendChild(tc);
    tableBody.appendChild(tr);  
    tableBody.appendChild(tr2);
    console.log(resArray)

    if(resArray.directSuper){
        var dS = "approved";
    }
    else if(!resArray.directSuper){
        var dS = "denied";
    }
    else if(resArray.directSuper == null){
        var dS = "pending";
    }
    if(resArray.departmentHead){
        var dH = "approved";
    }
    else if(!resArray.departmentHead){
        var dH = "denied";
    }
    else{
        var dH = "pending";
    }
    if(resArray.benCo){
        var bC = "approved";
    }
    else if(!resArray.benCo){
        var bC = "denied";
    }
    else{
        var bC = "pending";
    }

    let infoArray = [resArray.employeeUser, '|','$',resArray.reimburse, '|',resArray.courseStartdate, '|', resArray.place,'|',resArray.description,'|',resArray.justification,'|', resArray.grading,'|', resArray.pass,'|', resArray.directSuper,'|', resArray.departmentHead,'|', resArray.benCo,'|', resArray.approved,'|', resArray.urgent];
    let headerArray = ['Employee|','Reimbursement|', 'Course Date|', 'Place|', 'Description|', 'Justification|', 'Grading|','Passed|', 'DSApp|','DHApp|','BencoApp|','Approved|', 'Urgent'];
   
    // let infoArray2 = [];
    // let 2ndheader = ['Supervisor Approval', 'Department Head Approval', 'Benefactor Approval'];
    for(var i=0; i<headerArray.length; i++){
        let td = document.createElement('TD');
        td.innerHTML = headerArray[i];
        th.appendChild(td);
    }
    for(var j=0; j< infoArray.length; j++){
        let tInfo= document.createElement('TD');
        tInfo.innerHTML = infoArray[j];
        tc.appendChild(tInfo);
        
    }
    
      let pass = document.createElement('form');
        pass.setAttribute('method' , 'POST');
        pass.setAttribute('action', 'departmenthead');
        pass.setAttribute('id', 'approval');
    pass.innerHTML = (`
            <label for="approval"> Approve/Deny Request: </label>
            <select name="approval" id="approval">
            <option value = 0 > Select Choice</option>
            <option value = "approve" >Approve</option>
            <option value = "deny" >Deny</option>
            </select><br><h6>(If denied add a reason)<h6>
            <textarea name="reason" id="reason" maxlength="150" placeholder="150 Max Characters"></textarea>
            <label for="additional"> Request More Info: </label>
            <select name="additional" id="additional">
            <option value = 0 > Select Choice</option>
            <option value = "true" >Request</option>
            </select>`);

    
    let label = document.createElement('label');
    label.setAttribute('for', 'approval_id');
    label.innerHTML = "Approval Id";
    pass.appendChild(label);

    let inputTag = document.createElement('select');
    inputTag.setAttribute('id', 'approvalId');
    inputTag.setAttribute('name', 'approvalId');
    pass.appendChild(inputTag);

    let option = document.createElement('option');
    option.setAttribute('value', resArray.id);
    option.innerHTML = resArray.id;
    inputTag.appendChild(option);
    pass.appendChild(document.createElement("br"));

    let buttonTag = document.createElement('button');
    buttonTag.setAttribute('type', 'submit');
    buttonTag.setAttribute('value', 'Submit');
    buttonTag.setAttribute('onclick', 'confirmation()');
    buttonTag.setAttribute('class', "btn btn-sm btn-primary")
    buttonTag.innerHTML = 'Submit';
    //pass.appendChild(inputTag);
    pass.appendChild(buttonTag);
    
    tagTable.appendChild(pass);
    dataSection.appendChild(tagTable);
    dataSection.appendChild(tagBr);
    dataSection.appendChild(tagBr);
    dataSection.appendChild(pass);
    dataSection.appendChild(tagBr);

}

function benRequests(){
    let baseUrl = 'http://localhost:8080/p1/directsupervisor?';

  
    console.log('Button clicked');

    let xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', baseUrl, true); // true is the default argument - meaning that this is async

    // STEP 4: Send the request
    xhttp.send(); // for get requests, the send function does not have any arguments

    function receiveData() {
       
        let dataSection = document.getElementById('show');
        dataSection.innerHTML = '';
        
        let tagh3 = document.createElement('h3');
        tagh3.innerHTML = "These are your reimbursement requests: "
        dataSection.appendChild(tagh3); 
        // Check if the readyState is 'DONE' aka '4' and if the HTTP Status is 'ok' 200
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let r = xhttp.responseText;
            // console.log(r);

            r = JSON.parse(r);
            // r2 = JSON.parse(r2);
            // console.log(r);
            //might require for loop
            for(var i=0; i<r.length; i++)
            benpopulateData(r,i);

        }

    }
}


function benpopulateData(res, index) {

    // This is where we will do our DOM manipulation
    let dataSection = document.getElementById('show');
    // Create a new element
    let tagBr = document.createElement('br');
    

    // Append this new element to the section element (adding it as a child node in the DOM)
    // dataSection.appendChild(tagh3);

    // Now we'll use the same process for the Abilities
    let resArray = res[index];
    console.log(resArray);
    
    let tagTable = document.createElement('TABLE');
    tagTable.border = '2';

    // let table2 = document.createElement('TABLE');
    // let tableBody2 = document.createElement('TBODY');

    // table2.appendChild(tableBody2);    

    let tableBody = document.createElement('TBODY');
    tagTable.appendChild(tableBody);
    let tr = document.createElement('TR');
    let th = document.createElement('TH');
    let tc = document.createElement('TC');
    let tr2 = document.createElement('TR');
    // tr.innerHTML = ("id ");
    tr.appendChild(th);
    tr2.appendChild(tc);
    tableBody.appendChild(tr);  
    tableBody.appendChild(tr2);

    if(resArray.directSuper){
        var dS = "approved";
    }
    else if(!resArray.directSuper){
        var dS = "denied";
    }
    else if(resArray.directSuper == null){
        var dS = "pending";
    }
    if(resArray.departmentHead){
        var dH = "approved";
    }
    else if(!resArray.departmentHead){
        var dH = "denied";
    }
    else{
        var dH = "pending";
    }
    if(resArray.benCo){
        var bC = "approved";
    }
    else if(!resArray.benCo){
        var bC = "denied";
    }
    else{
        var bC = "pending";
    }


    let infoArray = [resArray.employeeUser, '|','$',resArray.reimburse, '|',resArray.courseStartdate, '|', resArray.place,'|',resArray.description,'|',resArray.justification,'|', resArray.grading,'|', resArray.pass,'|', resArray.directSuper,'|', resArray.departmentHead,'|', resArray.benCo,'|', resArray.approved,'|', resArray.urgent];
    let headerArray = ['Employee|','Reimbursement|', 'Course Date|', 'Place|', 'Description|', 'Justification|', 'Grading|','Passed|', 'DSApp|','DHApp|','BencoApp|','Approved|', 'Urgent'];
    // let infoArray2 = [];
    // let 2ndheader = ['Supervisor Approval', 'Department Head Approval', 'Benefactor Approval'];
    for(var i=0; i<headerArray.length; i++){
        let td = document.createElement('TD');
        td.innerHTML = headerArray[i];
        th.appendChild(td);
    }
    for(var j=0; j< infoArray.length; j++){
        let tInfo= document.createElement('TD');
        tInfo.innerHTML = infoArray[j];
        tc.appendChild(tInfo);
        
    }
      let pass = document.createElement('form');
        pass.setAttribute('method' , 'POST');
        pass.setAttribute('action', 'benco');
        pass.setAttribute('id', 'approval');
    pass.innerHTML = (`
            <label for="approval"> Approve/Deny Request: </label>
            <select name="approval" id="approval">
            <option value = 0 > Select Choice</option>
            <option value = "approve" >Approve</option>
            <option value = "deny" >Deny</option>
            </select><br><h6>(If denied add a reason)<h6>
            <textarea name="reason" id="reason" maxlength="150" placeholder="150 Max Characters"></textarea>
            <label for="additional"> Request More Info: </label>
            <select name="additional" id="additional">
            <option value = 0 > Select Choice</option>
            <option value = "true" >Request</option>
            </select><br><label for="changeAmount">
            Change Reimbursement Amount:
            <input id="changeAmount" type="number" min="0" name="changeAmount" placeholder="$X.XX">
            </label>
                    `);

    
    let label = document.createElement('label');
    label.setAttribute('for', 'approval_id');
    label.innerHTML = "Approval Id";
    pass.appendChild(label);

    let inputTag = document.createElement('select');
    inputTag.setAttribute('id', 'approvalId');
    inputTag.setAttribute('name', 'approvalId');
    pass.appendChild(inputTag);

    let option = document.createElement('option');
    option.setAttribute('value', resArray.id);
    option.innerHTML = resArray.id;
    inputTag.appendChild(option);
    pass.appendChild(document.createElement("br"));

    let buttonTag = document.createElement('button');
    buttonTag.setAttribute('type', 'submit');
    buttonTag.setAttribute('value', 'Submit');
    buttonTag.setAttribute('onclick', 'confirmation()');
    buttonTag.setAttribute('class', "btn btn-sm btn-primary")
    buttonTag.innerHTML = 'Submit';
    //pass.appendChild(inputTag);
    pass.appendChild(buttonTag);
    
    tagTable.appendChild(pass);
    dataSection.appendChild(tagTable);
    dataSection.appendChild(tagBr);
    dataSection.appendChild(tagBr);
    dataSection.appendChild(pass);
    dataSection.appendChild(tagBr);

}