(function(document) {
    'use strict';

    let TableFilter = (function(myArray) {
        let search_input;

        function _onInputSearch(e) {
            search_input = e.target;
            let table = document.getElementById("itemsTable");
            myArray.forEach.call(table.tBodies, function(tbody) {
                myArray.forEach.call(tbody.rows, function(row) {
                    var text_content = row.textContent.toLowerCase();
                    var search_val = search_input.value.toLowerCase();
                    row.style.display = text_content.indexOf(search_val) > -1 ? '' : 'none';
                });
            });
        }

        return {
            init: function() {
                var inputs = document.getElementsByClassName('form-control');
                myArray.forEach.call(inputs, function(input) {
                    input.oninput = _onInputSearch;
                });
            }
        };
    })(Array.prototype);

    document.addEventListener('readystatechange', function() {
        if (document.readyState === 'complete') {
            TableFilter.init();
        }
    });

})(document);
function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchBar");
  filter = input.value.toUpperCase();
  table = document.getElementById("itemsTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    tds = tr[i].getElementsByTagName("td");
    Array.from(tds).forEach( (td) => {
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    )

  }
}