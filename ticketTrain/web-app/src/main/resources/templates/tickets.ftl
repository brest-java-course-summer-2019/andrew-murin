<!DOCTYPE html>
<!--suppress ALL -->
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <link rel="stylesheet"
          href="/css/style.css">

    <title>Tickets</title>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a class="navbar-brand"
           href="/index">
            <img src="/img/account.png"
                 width="30" height="30" alt="logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Togglenavigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link"
                       href="/search-tickets">Search</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link"
                       href="/tickets">Schedule</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link"
                       href="/paid-tickets">Paid tickets</a>
                </li>
            </ul>
        </div>

    </nav>

    <header>
        <nav id="main-header" class="py-1 mb-3 navbar navbar-expand-sm navbar-light bg-light text-dark">
            <span class="navbar-brand mr-auto">Train schedule</span>
            <ul class="navbar-nav float-right">
                <li>
                <a href="/ticket"
                   class="btn btn-dark btn"
                   data-toggle="tooltip"
                   data-placement="left"
                   title="Save ticket">
                    <i class="fas fa-save"></i>
                    <span class="d-none d-sm-inline">&nbsp;Add</span></a>
                </li>
            </ul>
        </nav>
    </header>

    <section>
        <div class="container-fluid">
            <div class="row">
                <div class="col">
                    <table class="table table-striped">
                        <thead class="thead-inverse">
                            <tr>
                                <th>Train</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Cost</th>
                                <th>Date</th>
                                <th></th>
                            </tr>
                        </thead>


                        <tbody>
                            <tr>
                            <#list tickets as ticket>
                                <td>${ticket.ticketId}</td>
                                <td>${ticket.fromCity.cityName}</td>
                                <td>${ticket.toCity.cityName}</td>
                                <td>${ticket.ticketCost}</td>
                                <td>${ticket.ticketDate}</td>
                                <td>
                                    <span class="navbar-nav float-right"
                                            attr="data-id=${ticket.ticketId}">
                                        <a href="/pay-ticket/${ticket.ticketId}"
                                            class="btn btn-dark"
                                            title="Pay ticket"
                                            data-toggle="tooltip"
                                            data-placement="top">
                                                <i class="fa fa-pencil"></i>
                                            <span class="d-none d-md-inline">Pay</span></a>

                                    </span>
                                </td>
                                <td>
                                    <span data-toggle="modal" data-target="#deleteDialog"
                                          class="navbar-nav float-right"
                                          data-id="${ticket.ticketId}"
                                          data-name="${ticket.ticketId}">
                                        <a href="#" class="btn btn-dark"
                                           title="delete ticket"
                                           data-toggle="tooltip"
                                           data-placement="top">
                                            <i class="fa fa-remove"></i>
                                            <span class="d-none d-md-inline">Delete</span></a>
                                    </span>

                                    <span class="navbar-nav float-right">
                                        <a href="/ticket/${ticket.ticketId}"
                                            class="btn btn-dark"
                                            title="Edit ticket"
                                            data-toggle="tooltip"
                                            data-placement="top">
                                                <i class="fa fa-pencil"></i>
                                            <span class="d-none d-md-inline">Edit</span></a>

                                    </span>
                                </td>
                            </tr>
                            </#list>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </section>

     <!--Confirm delete Modal-->
    <div class="modal fade" id="deleteDialog" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header alert-danger">
                    <h5 class="modal-title" id="exampleModalLabel">Delete ticket</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Please confirm delete ticket
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <a id="deleteUrl" href="#" class="btn btn-danger">Delete</a>
                </div>
            </div>
        </div>
    </div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <script>
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
        $('#deleteDialog').on('show.bs.modal', function (event) {
            var target = $(event.relatedTarget)
            $(this).find('.modal-body').text('Please confirm delete ticket: "' + target.data('name') + '"')
            document.getElementById('deleteUrl').href = '/ticket/' + target.data('id') + '/delete';
        })
    </script>

</body>
</html>