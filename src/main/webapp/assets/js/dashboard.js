let imageUpload = document.getElementById("user_profile_image");
console.log(imageUpload);
window.onload = fetchempdetails;
//window.onload = retriveImage;
imageUpload.addEventListener('submit', async (e) => {
  e.preventDefault();
  e.stopPropagation();
  if (imageUpload.checkValidity() === true) {
    document.getElementById("upload").style.display = "none";
    document.getElementById("spinner-button-upload").style.display = "block";

    let form_data = new FormData();
    form_data.append('file', document.getElementById('formFile').files[0]);
    form_data.append('emp_id', sessionStorage.getItem('eid'));
    console.log(form_data);
    let response = await fetch('api/employee/uploadImage', {
      method: 'POST',
      body: form_data
    });
    try {
      let res = response.json();
      document.getElementById("upload").style.display = "block";
      document.getElementById("spinner-button-upload").style.display = "none";

      console.log("Update Successful");
      window.alert("Profile Updated Successfully click ok to continue");
      location.href = "dashboard.html";
      //fetchempdetails();
    } catch (err) {
      console.log(err);
      document.getElementById("upload").style.display = "block";
      document.getElementById("spinner-button-upload").style.display = "none";
    }
  }
});
async function fetchempdetails() {
  console.log("fetch_emp_details");
  console.log(sessionStorage.getItem(('eid')));
  if (!sessionStorage.getItem('eid')) {
    location.href = "index.html";
    return;
  }
  console.log("Sending Req");
  let response = await fetch('api/employee/login',{
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    },
    body: JSON.stringify({
      email:sessionStorage.getItem("emailid"),
    })
  });

  console.log("Response Received");
  let employee = await response.json(); // read response body and parse as JSON
  console.log("Response");
  console.log(employee);
  document.getElementById("EmpName").innerHTML+=employee['firstname']+" "+employee['lastname'];
  document.getElementById("Eid").innerHTML += employee['employeeid'];
  document.getElementById("Email").innerHTML += employee['email'];
  console.log(employee['photograph_path']);
  if(employee['photograph_path'] != null || employee['photograph_path'] != "null" )
  {
    let path = "api/employee/images/" + employee['photograph_path'];
    console.log(path);
    document.getElementById("user_img").src = path;
    console.log("src path set");
  }

}
let id=sessionStorage.getItem("eid");
let name=sessionStorage.getItem("fname");
let salary_query=document.getElementById("date_search");
let x=0;
console.log(salary_query);
salary_query.addEventListener("submit",async (e)=>
{
  e.preventDefault();
  e.stopPropagation();
  console.log(document.getElementById("day1"));
  sessionStorage.setItem("sdate",document.getElementById('day1').value);
  if (salary_query.checkValidity() === true) {
    let response = await fetch('api/salary/getsalary', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8'
      },
      body: JSON.stringify({
        employeeid: sessionStorage.getItem("eid"),
        date : document.getElementById("day1").value,
      })
    });

    try
    {
      let result = await response.json();
      console.log(result);
      //console.log(result[0]['employee_employeeid'],result[0]['amount']);
      let salary_table = document.getElementById('EmpSalary');
      salary_table.innerHTML='<thead><tr><th>Entry no.</th><th>Amount</th><th>Payment Date(UTC)</th><th>Description</th></tr></thead><tbody>';
      for (let i = 0; i < result.length; i++) {
        salary_table.innerHTML += '<tr><td>'+result[i]['id']+'</td><td>'+result[i]['amount']+'</td><td>'+result[i]['payment_date']+'</td><td>'+result[i]['description']+'</td></tr>';
      }
      salary_table.innerHTML += '</tbody>';
      x=1;
      if(result.length===0)
        x=2;
    }
    catch(e)
    {
      x=2;
      console.log("something wrong!!!!");
    }
  }
});

function generate() {
  if(x===1){
  var doc = new jsPDF('p', 'pt');
  doc.autoTable({html: '#EmpSalary'});
  let finalY=doc.previousAutoTable.finalY;
  doc.setFontSize(8);
  doc.text(30,finalY+10,"Salary Disbursment History upto         "+sessionStorage.getItem("sdate"));
  doc.text(30,finalY+50,"For EmployeeId                  "+id);
  doc.save('table.pdf');}
  else if(x===2){
    alert("You don't have any salary disbursements until the given date!");
  }
  else
  {
    alert("please select a date!");
  }
}
(function () {
  'use strict'

  feather.replace()

  // Graphs
  var ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday'
      ],
      datasets: [{
        data: [
          15339,
          21345,
          18483,
          24003,
          23489,
          24092,
          12034
        ],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
})()
