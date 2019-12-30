<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <link rel="stylesheet"
          href="/css/style.css">
    <title>Search</title>
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
    <nav id="main-header"
         class="py-1 mb-3 navbar navbar-expand-sm navbar-light bg-light text-dark">
        <span class="navbar-brand mr-auto">Choise direction</span>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#"
                       onclick="document.getElementById('FormSearchDate').submit();"
                       class="btn btn-dark"
                       title="Search tickets"
                       data-toggle="tooltip"
                       data-placement="bottom">
                    <i class="fas fa-undo"></i>
                        <span class="d-none d-sm-inline">&nbsp;Search</span></a>
                </li>
            </ul>
    </nav>
</header>

<section id="ticket">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <div class="card-body">
                    <form id ="FormSearchDate"
                          method="post"
                          action="/search-tickets"

                        <tr>
                            <div class="form-group">
                                <label>Select from a city</label>
                                <select class="form-control" id="directionFrom" name="directionFrom">
                                    <option value=1>BREST</option>
                                    <option value=2>MINSK</option>
                                    <option value=3>VITEBSK</option>
                                    <option value=4>GOMEL</option>
                                    <option value=5>GRODNO</option>
                                    <option value=6>MOGILEV</option>
                                </select>
                            </div>
                        </tr>

                        <tr>
                            <div class="form-group">
                                <label>Select to a city</label>
                                <select class="form-control" id="directionTo" name="directionTo">
                                    <option value=1>BREST</option>
                                    <option value=2>MINSK</option>
                                    <option value=3>VITEBSK</option>
                                    <option value=4>GOMEL</option>
                                    <option value=5>GRODNO</option>
                                    <option value=6>MOGILEV</option>
                                </select>
                            </div>
                        </tr>

                        <tr><td><label>Select a date</label>
                                <div class="form-group">
                                    <input id="startDate"
                                           name="startDate"
                                           type="date"
                                           class="form-control"
                                           value="">
                                </div></td>
                        </tr>
                        <tr><td><label>Select a date</label>
                                <div class="form-group">
                                    <input id="finishDate"
                                           name="finishDate"
                                           type="date"
                                           class="form-control"
                                           value="">
                                </div></td>
                        </tr>
                    </form>

                </div>
            </div>
        </div>
    </div>
</section>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>

</body>
</html>