<%-- 
    Document   : index
    Created on : 27/12/2021, 09:03:58 PM
    Author     : rozo
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="Jose Eduardo Rozo Molina" content="joseeduardorozomolina@gmail.com" />
        <title>Inforet</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap Icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- SimpleLightbox plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top"> 


        <div class="cuadroredes">
            <ul class="social-icons margin-top-10">
                <li  data-placement="bottom" data-original-title="Síguenos en Facebook"><a href="https://www.facebook.com/inforetsas" class="rounded social_facebook"></a>
                </li>
                <li  data-placement="bottom" data-original-title="Síguenos en Twitter"><a href="https://twitter.com/INFORET" class="rounded social_twitter"></a></li>
                <li  data-placement="bottom" data-original-title="Síguenos en Youtube"><a href="https://www.youtube.com/channel/UCgPz-qqdsgfdg3k2g" class="rounded social_youtube"></a></li>
                <li  data-placement="bottom" data-original-title="Síguenos en Instagram">
                    <a href="https://www.instagram.com/inforet/" class="rounded social_instagram"></a>
                </li>
             </ul>
        </div>

          <%
                Map<String, String> textos = (Map<String, String>) request.getSession().getAttribute("textos");
               Map<String, String> imagenes = (Map<String, String>) request.getSession().getAttribute("imagenes");

                
            %>

        

        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container px-4 px-lg-5">
                <img id="imglogo" src="<%=imagenes.get("1")%>" height="50px" style="display:none" alt="logo">
                <a class="navbar-brand" href="#page-top">INFORET SAS</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#about">Sobre nosotros</a></li>
                        <li class="nav-item"><a class="nav-link" href="#services">Servicios</a></li>
                        <li class="nav-item"><a class="nav-link" href="#portfolio">Portafolio</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact">Contacto</a></li>
                        <li class="nav-item"><a class="nav-link" href="../Login">Inicio</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead">
            <div class="container px-4 px-lg-5 h-100">
                <div class="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
                    <div class="col-lg-8 align-self-end">
                        <h1 class="text-white font-weight-bold"><%=textos.get("0")%></h1>
                        <hr class="divider" />
                    </div>
                    <div class="col-lg-8 align-self-baseline">
                        <p class="text-white-75 mb-5"><%=textos.get("1")%></p>
                        <a class="btn btn-primary btn-xl" href="#about">Saber mas</a>
                    </div>
                </div>
            </div>
        </header>
        <!-- About-->
        <section class="page-section bg-primary" id="about">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 text-center">
                        <h2 class="text-white mt-0"><%=textos.get("2")%></h2>
                        <hr class="divider divider-light" />
                        <p class="text-white-75 mb-4"><%=textos.get("3")%></p>
                        <a class="btn btn-light btn-xl" href="#services">Empezar!</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Services-->
        <section class="page-section" id="services">
            <div class="container px-4 px-lg-5">
                <h2 class="text-center mt-0"><%=textos.get("5")%></h2>
                <hr class="divider" />
                <div class="row gx-4 gx-lg-5">
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-server fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Servidores</h3>
                            <p class="text-muted mb-0"><%=textos.get("4")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-laptop fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Aplicación web</h3>
                            <p class="text-muted mb-0"><%=textos.get("6")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-bag fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">E-Commerce</h3>
                            <p class="text-muted mb-0"><%=textos.get("7")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-wifi fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Redes</h3>
                            <p class="text-muted mb-0"><%=textos.get("8")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col- md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi bi-people fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Capacitación</h3>
                            <p class="text-muted mb-0"><%=textos.get("9")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-camera-video fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Seguridad</h3>
                            <p class="text-muted mb-0"><%=textos.get("10")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi bi-globe fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">VPN</h3>
                            <p class="text-muted mb-0"><%=textos.get("11")%></p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-telephone fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Telefonía IP</h3>
                            <p class="text-muted mb-0"><%=textos.get("12")%></p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Portfolio2-->
        <div id="portfolio">
            <div class="container-fluid p-0">
                <div class="row g-0">
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/1.jpg" title="Project Name">
                            <img class="img-fluid" src="<%=imagenes.get("2")%>" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categorias</div>
                                <div class="project-name">Redes</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/2.jpg" title="Project Name">
                            <img class="img-fluid" src="<%=imagenes.get("3")%>" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categorias</div>
                                <div class="project-name">Aplicaciones web</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/3.jpg" title="Project Name">
                            <img class="img-fluid" src="<%=imagenes.get("4")%>" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categorias</div>
                                <div class="project-name">E commerce</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/4.jpg" title="Project Name">
                            <img class="img-fluid" src="<%=imagenes.get("5")%>" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categorias</div>
                                <div class="project-name">Servidores</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/5.jpg" title="Project Name">
                            <img class="img-fluid" src="<%=imagenes.get("6")%>" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">Categorias</div>
                                <div class="project-name">Consultoria</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="assets/img/portfolio/fullsize/6.jpg" title="Project Name">
                            <img class="img-fluid" src="<%=imagenes.get("7")%>" alt="..." />
                            <div class="portfolio-box-caption p-3">
                                <div class="project-category text-white-50">Categorias</div>
                                <div class="project-name">Capacitación</div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Call to action-->
        <section class="page-section bg-dark text-white">
            <div class="container px-4 px-lg-5 text-center">
                <h2 class="mb-4"><%=textos.get("15")%></h2>
                <div class ="row" >
                    <div class ="col-lg-4">
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">AMP</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">CISCO</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">MERAKI</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">AMP</p>
                    </div>
                    <div class ="col-lg-4">
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">SIEMON</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">TRIPPLITE</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">AXIS</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">HIKEVISION</p>
                    </div>
                    <div class ="col-lg-4">
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">L-COM</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">MICROLINK</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">FORTINET</p>
                        <p class="text-white-75 mb-4" style = "font-family: Merriweather Sans;">ARUBA</p>
                    </div>

                </div>                

            </div>
        </section>
        <!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0"><%=textos.get("13")%></h2>
                        <hr class="divider" />
                        <p class="text-muted mb-5"><%=textos.get("14")%></p>
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                    <div class="col-lg-6">
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- * * SB Forms Contact Form * *-->
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- This form is pre-integrated with SB Forms.-->
                        <!-- To make this form functional, sign up at-->
                        <!-- https://startbootstrap.com/solution/contact-forms-->
                        <!-- to get an API token!-->
                        <form id="contactForm" data-sb-form-api-token="API_TOKEN" action="RegisterClientProveedor.do">
                            <!-- Business input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="Business" name="Business" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">Razon Social</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                            </div>
                            <!-- Type-->   

                            <div class="form-floating mb-3">                              
                                <select class="form-control" name="type" id="type" required>
                                    <option disabled selected >Tipo Persona</option>
                                    <option value="NATURAL" name="natural">Natural</option>
                                    <option value="JURIDICA" name="juridica">Juridica</option>
                                </select>
                            </div>
                            <!-- Numero Nit-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="Nit" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">Nit</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Nit is requerido.</div>
                            </div>
                            <!-- Pais-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="Country" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">Pais</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Pais is required.</div>
                            </div>
                            <!-- Direccion -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="address" type="text" placeholder="Enter your address..." data-sb-validations="required" />
                                <label for="name">Dirección</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A address is required.</div>
                            </div>
                            <!-- postal -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="Code" type="text" placeholder="Enter your Postal Code..." data-sb-validations="required" />
                                <label for="name">Codigo Postal</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Postal Code is required.</div>
                            </div>

                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                <label for="email">Email</label>
                                <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                                <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                            </div>
                            <!-- Phone number input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="phone" name="number" type="tel" placeholder="(123) 456-7890" data-sb-validations="required" />
                                <label for="phone">Numero de Telefono</label>
                                <div class="invalid-feedback" data-sb-feedback="phone:required">A phone number is required.</div>
                            </div>
                            <!-- nombres -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="nombre" name="nombre" type="text" placeholder="Enter you Names..." data-sb-validations="required" />
                                <label for="name">Nombres </label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Postal Code is required.</div>
                            </div>
                            <!-- apellido1 -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="nombre" name="apellido1" type="text" placeholder="Enter you last name..." data-sb-validations="required" />
                                <label for="name">Primer Apellido </label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Postal Code is required.</div>
                            </div>
                            <!-- apellido2 -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="nombre" name="apellido2" type="text" placeholder="Enter you second  Names..." data-sb-validations="required" />
                                <label for="name">Segundo Apellido</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Postal Code is required.</div>
                            </div>
                            <!-- fecha nac -->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="nacimiento" name="nacimiento" type="date"  data-sb-validations="required" />
                                <label for="name">Fecha De Nacimiento</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A Postal Code is required.</div>
                            </div>

                            <div class="form-floating mb-3">                              
                                <select class="form-control" name="tipous" id="genero" required>
                                    <option disabled selected >Seleccione</option>
                                    <option value="customer" name="customer">Cliente</option>
                                    <option value="supplier" name="supplier">Proveedor</option>
                                </select>
                            </div>

                            <!-- Message input-->
                            <div class="form-floating mb-3">
                                <textarea class="form-control" id="message" name="message" placeholder="Enter your message here..." style="height: 10rem" data-sb-validations="required"></textarea>
                                <label for="message">Mensaje</label>
                                <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div>
                            </div>

                            <!-- Submit Button-->
                            <div class="d-grid"><button class="btn btn-primary btn-xl colort " id="submitButton" type="submit">Enviar</button></div>
                        </form>
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-4 text-center mb-5 mb-lg-0">
                        <i class="bi-phone fs-2 mb-3 text-muted"></i>
                        <div>+57 (312) 123-4567</div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="bg-light py-5">
            <div class="container px-4 px-lg-5"><div class="small text-center text-muted">2021 © All Rights Reserved. Desarrollado por: INFORET SAS - Jose Eduardo Rozo M</div></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- SimpleLightbox plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>

