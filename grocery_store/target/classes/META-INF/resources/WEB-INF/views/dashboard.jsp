<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grocery Store</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Grocery Store</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">

            <!-- Sidebar -->
            <div class="col-md-3 col-lg-2 d-none d-md-block bg-light sidebar p-3">
                <h5 class="text-center">Categories</h5>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Fruits</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Vegetables</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Dairy</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Beverages</a>
                    </li>
                </ul>
            </div>

            <!-- Main Content -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="row mt-4">

                    <!-- Product Card -->
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card h-100">
                            <div class="card-body text-center">
                                <h5 class="card-title">Product Name</h5>
                                <p class="card-text">$10.00</p>
                                <form action="/buy" method="post">
                                    <input type="hidden" name="productId" value="1"> <!-- Product ID for example -->
                                    <button type="submit" class="btn btn-success btn-sm">Buy Now</button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Repeat Product Card (use a loop to render multiple products dynamically) -->
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card h-100">
                            <div class="card-body text-center">
                                <h5 class="card-title">Product Name</h5>
                                <p class="card-text">$15.00</p>
                                <form action="/buy" method="post">
                                    <input type="hidden" name="productId" value="2"> <!-- Product ID for example -->
                                    <button type="submit" class="btn btn-success btn-sm">Buy Now</button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Add more cards as needed -->

                </div>
            </main>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
