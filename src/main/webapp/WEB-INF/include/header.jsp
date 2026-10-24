<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Booking System</title>
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/assets/css/bootstrap.css" >
    </head>
    <body class="d-flex flex-column min-vh-100 bg-light">

        <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">HOTEL BOOKING</a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Rooms</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About Us</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact</a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                System Management
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Manage Room Types</a></li>
                                <li><a class="dropdown-item" href="#">Manage Rooms</a></li>
                                <li><a class="dropdown-item" href="#">Manage Bookings</a></li>
                                <li><a class="dropdown-item" href="#">Check-in / Check-out</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Manage Customers</a></li>
                                <li><a class="dropdown-item" href="#">Manage Reviews</a></li>
                                <li><a class="dropdown-item text-warning fw-bold" href="#">Revenue Statistics</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="navbar-nav me-2">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Register</a>
                        </li>
                    </ul>   
                </div>
            </div>
        </nav>

        <main class="container mt-5 flex-grow-1">