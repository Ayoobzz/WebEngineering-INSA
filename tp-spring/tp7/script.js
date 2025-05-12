
function getHelloWorld() {
  const apiURL = 'http://localhost:8080/api/v1/public/hello/helloworld';
  const xhr = new XMLHttpRequest();

  xhr.addEventListener("loadend", evt => {
	  if(evt.target.readyState === 4) {
	    console.log('Query 1 executed with success');
	    console.log(evt.target.responseText);

	    const helloTag = document.getElementById("hello");
	    helloTag.innerHTML = evt.target.responseText;
	}
  });

  xhr.open("GET", apiURL); 
  xhr.send();
}

function getTodoById(id) {
    const apiURL = 'http://localhost:8080/api/v2/public/todo/' + id;
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("loadend", evt => {
        if(evt.target.readyState === 4) {
            console.log('Query 2 executed with success');
            console.log(evt.target.responseText);

            const todoTag = document.getElementById("todo");
            todoTag.innerHTML = evt.target.responseText;
        }
    });

    xhr.open("GET", apiURL);
    xhr.send();
}

getHelloWorld();
getTodoById(1);
