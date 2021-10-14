function showEmpRequests(){

    let baseUrl = 'http://localhost:8080/p1/employeereimbursement?';

    // console.log('Button clicked');

    //let userInput = document.getElementById('dataInput').value;
    //console.log('User has input: ' + userInput);
    // let url = baseUrl;// + userInput;
    
    // 4 Steps to building an AJAX call

    // STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', baseUrl, true); // true is the default argument - meaning that this is async

    // STEP 4: Send the request
    xhttp.send(); // for get requests, the send function does not have any arguments

    function receiveData() {
        /**
         * Different ready states of an XMLHttpRequest Object
         * 
         * 0: Unsent
         * 1: Opened (not sent yet)
         * 2: Headers Received (we have now sent)
         * 3: Loading (server is doing what it needs to process the request)
         * 4: Done (server has sent back response)
         */

        // before we parse the response and populate the data, 
        // let's empty out what's inside the data section element
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
            // console.log(r);
            //might require for loop
            for(var i=0; i<r.length; i++){
                populateData(r,i);
            }
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

    // console.log(resArray.employeeId);

    // Create an unordered list element
    // let abilities = document.createElement('ul');
    // dataSection.innerHTML += 'Abilities<br>';
    // dataSection.appendChild(abilities);

    // TODO - add sprites to the rendered page and capitalize words. 
    // console.log(spritesObject);
    let tagTable = document.createElement('TABLE');
    tagTable.border = '2';
    let formTag = document.createElement('FORM');
    formTag.setAttribute('method' , 'POST');
    formTag.setAttribute('action', 'gradechange');
    formTag.setAttribute('name', 'gradingType');
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
    let infoArray = ['$',resArray.reimburse, '|', resArray.grading,'|', resArray.pass,'|', resArray.approved,'|',resArray.urgent,'|',resArray.additionalInfoSb,'|',resArray.additionalInfoHb,'|',resArray.additionalInfoBenb];
    let headerArray = [' Reimbursement|', 'Grading|','Passed|', 'Approved|', 'Urgent|', 'Additional Super Info|','Additional Head Info|','Additional Benco Info'];
    //if(resArray.pass == 'N/A'){

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
        pass.setAttribute('action', 'gradechange');
        pass.setAttribute('id', 'gradingType');
        pass.innerHTML = (`
        <label for="gradingType"> Input Grading Format: </label>
        <select name="gradingType" id="gradingType">
        <option value = 0 > Select Grade</option>
        <option value = "A" >A</option>
        <option value = "B" >B</option>
        <option value = "C" >C</option>
        <option value = "D" >D</option>
        <option value = "F" >F</option>
        </select>
        <br><h6>(If asked for additional Info)<h6>
            <textarea name="additionalInfo" id="additionalInfo" maxlength="150" placeholder="150 Max Characters"></textarea>
            `);
        
        let label = document.createElement('label');
        label.setAttribute('for', 'reimbursement_id');
        label.innerHTML = "Request Id";
        pass.appendChild(label);

        let inputTag = document.createElement('select');
        inputTag.setAttribute('id', 'reimbursementId');
        inputTag.setAttribute('name', 'reimbursementId');
        pass.appendChild(inputTag);

        let option = document.createElement('option');
        option.setAttribute('value', resArray.id);
        option.innerHTML = resArray.id;
        inputTag.appendChild(option);
        pass.appendChild(document.createElement("br"));
        console.log(resArray);

        if(resArray.amountIncrease == true){

        let label2 = document.createElement('label');
        label2.setAttribute('for', 'amountChange');
        label2.innerHTML = ("Benco Changed Amount!New Amount:$" + resArray.reimburse + " Accept/Decline Amount");
        pass.appendChild(label2);
        pass.appendChild(document.createElement('br'));

        let inputTag2 = document.createElement('select');
        inputTag2.setAttribute('id', 'amountChange');
        inputTag2.setAttribute('name', 'amountChange');
        pass.appendChild(inputTag2);

        let option2 = document.createElement('option');
        option2.setAttribute('value', "0");
        option2.innerHTML = "Select";
        inputTag2.appendChild(option2);

        let option3 = document.createElement('option');
        option3.setAttribute('value', "accept");
        option3.innerHTML = "Accept";
        inputTag2.appendChild(option3);

        let option4 = document.createElement('option');
        option4.setAttribute('value', "decline");
        option4.innerHTML = "Decline";
        inputTag2.appendChild(option4);
        pass.appendChild(document.createElement("br"));
    }
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
        
        
   // }

}

function confirmation(){
    alert("Submitted Grade");
}