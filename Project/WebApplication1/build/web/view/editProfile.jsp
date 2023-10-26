<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />

    <title>bs4 edit profile page - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- bootstrap 3.0.2 -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- font Awesome -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Morris chart -->
    <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- fullCalendar -->
    <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
    <!-- Daterange picker -->
    <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- iCheck for checkboxes and radio inputs -->
    <link href="css/iCheck/all.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <!-- Theme style -->
    <link href="css/style.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
      body {
        margin-top: 20px;
        background: #f8f8f8;
      }
    </style>
  </head>
  <body class="skin-black">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <header class="header">
      <a href="index.html" class="logo">
          Director
      </a>
      <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-expand-lg navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
      </a>
      <div class="navbar-collapse collapse">
          <ul class="nav navbar-right ">
              <!-- Messages: style can be found in dropdown.less-->
              <li class=" dropdown messages-menu">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <i class="fa fa-envelope"></i>
                      <span class="label label-success">4</span>
                  </a>
                  <ul class="dropdown-menu">
                      <li class="header">You have 4 messages</li>
                      <li>
                          <!-- inner menu: contains the actual data -->
                          <ul class="menu">
                              <li><!-- start message -->
                                  <a href="#">
                                      <div class="pull-left">
                                          <img src="img/26115.jpg" class="img-circle" alt="User Image"/>
                                      </div>
                                      <h4>
                                          Support Team
                                      </h4>
                                      <p>Why not buy a new awesome theme?</p>
                                      <small class="pull-right"><i class="fa fa-clock-o"></i> 5 mins</small>
                                  </a>
                              </li><!-- end message -->
                              <li>
                                  <a href="#">
                                      <div class="pull-left">
                                          <img src="img/26115.jpg" class="img-circle" alt="user image"/>
                                      </div>
                                      <h4>
                                          Director Design Team

                                      </h4>
                                      <p>Why not buy a new awesome theme?</p>
                                      <small class="pull-right"><i class="fa fa-clock-o"></i> 2 hours</small>
                                  </a>
                              </li>
                              <li>
                                  <a href="#">
                                      <div class="pull-left">
                                          <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                      </div>
                                      <h4>
                                          Developers

                                      </h4>
                                      <p>Why not buy a new awesome theme?</p>
                                      <small class="pull-right"><i class="fa fa-clock-o"></i> Today</small>
                                  </a>
                              </li>
                              <li>
                                  <a href="#">
                                      <div class="pull-left">
                                          <img src="img/26115.jpg" class="img-circle" alt="user image"/>
                                      </div>
                                      <h4>
                                          Sales Department

                                      </h4>
                                      <p>Why not buy a new awesome theme?</p>
                                      <small class="pull-right"><i class="fa fa-clock-o"></i> Yesterday</small>
                                  </a>
                              </li>
                              <li>
                                  <a href="#">
                                      <div class="pull-left">
                                          <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                      </div>
                                      <h4>
                                          Reviewers

                                      </h4>
                                      <p>Why not buy a new awesome theme?</p>
                                      <small class="pull-right"><i class="fa fa-clock-o"></i> 2 days</small>
                                  </a>
                              </li>
                          </ul>
                      </li>
                      <li class="footer"><a href="#">See All Messages</a></li>
                  </ul>
              </li>
              <li class="dropdown tasks-menu">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <i class="fa fa-tasks"></i>
                      <span class="label label-danger">9</span>
                  </a>
                  <ul class="dropdown-menu">
                      <li class="header">You have 9 tasks</li>
                      <li>
                          <!-- inner menu: contains the actual data -->
                          <ul class="menu">
                              <li><!-- Task item -->
                                  <a href="#">
                                      <h3>
                                          Design some buttons
                                          <small class="pull-right">20%</small>
                                      </h3>
                                      <div class="progress progress-striped xs">
                                          <div class="progress-bar progress-bar-success" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                              <span class="sr-only">20% Complete</span>
                                          </div>
                                      </div>
                                  </a>
                              </li><!-- end task item -->
                              <li><!-- Task item -->
                                  <a href="#">
                                      <h3>
                                          Create a nice theme
                                          <small class="pull-right">40%</small>
                                      </h3>
                                      <div class="progress progress-striped xs">
                                          <div class="progress-bar progress-bar-danger" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                              <span class="sr-only">40% Complete</span>
                                          </div>
                                      </div>
                                  </a>
                              </li><!-- end task item -->
                              <li><!-- Task item -->
                                  <a href="#">
                                      <h3>
                                          Some task I need to do
                                          <small class="pull-right">60%</small>
                                      </h3>
                                      <div class="progress progress-striped xs">
                                          <div class="progress-bar progress-bar-info" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                              <span class="sr-only">60% Complete</span>
                                          </div>
                                      </div>
                                  </a>
                              </li><!-- end task item -->
                              <li><!-- Task item -->
                                  <a href="#">
                                      <h3>
                                          Make beautiful transitions
                                          <small class="pull-right">80%</small>
                                      </h3>
                                      <div class="progress progress-striped xs">
                                          <div class="progress-bar progress-bar-warning" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                              <span class="sr-only">80% Complete</span>
                                          </div>
                                      </div>
                                  </a>
                              </li><!-- end task item -->
                          </ul>
                      </li>
                      <li class="footer">
                          <a href="#">View all tasks</a>
                      </li>
                  </ul>
              </li>
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <i class="fa fa-user"></i>
                      <span>Jane Doe <i class="caret"></i></span>
                  </a>
                  <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                      <li class="dropdown-header text-center">Account</li>

                      <li>
                          <a href="#">
                          <i class="fa fa-clock-o fa-fw pull-right"></i>
                              <span class="badge badge-success pull-right">10</span> Updates</a>
                          <a href="#">
                          <i class="fa fa-envelope-o fa-fw pull-right"></i>
                              <span class="badge badge-danger pull-right">5</span> Messages</a>
                          <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                              <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                          <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span class=
                              "badge pull-right">11</span> FAQ</a>
                      </li>

                      <li class="divider"></li>

                          <li>
                              <a href="#">
                              <i class="fa fa-user fa-fw pull-right"></i>
                                  Profile
                              </a>
                              <a data-toggle="modal" href="#modal-user-settings">
                              <i class="fa fa-cog fa-fw pull-right"></i>
                                  Settings
                              </a>
                              </li>

                              <li class="divider"></li>

                              <li>
                                  <a href="#"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                              </li>
                          </ul>
                      </li>
                  </ul>
              </div>
          </nav>
      </header>
    
    <div class="container">
      <div class="row flex-lg-nowrap">
        <div class="col-12 col-lg-auto mb-3" style="width: 200px">
          <div class="card p-3">
            <div class="e-navlist e-navlist--active-bg">
              <ul class="nav">
                <li class="nav-item">
                  <a class="nav-link px-2 active" href="#"
                    ><i class="fa fa-fw fa-bar-chart mr-1"></i
                    ><span>Overview</span></a
                  >
                </li>
                <li class="nav-item">
                  <a
                    class="nav-link px-2"
                    href="https://www.bootdey.com/snippets/view/bs4-crud-users"
                    target="__blank"
                    ><i class="fa fa-fw fa-th mr-1"></i><span>CRUD</span></a
                  >
                </li>
                <li class="nav-item">
                  <a
                    class="nav-link px-2"
                    href="https://www.bootdey.com/snippets/view/bs4-edit-profile-page"
                    target="__blank"
                    ><i class="fa fa-fw fa-cog mr-1"></i
                    ><span>Settings</span></a
                  >
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="row">
            <div class="col mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="e-profile">
                    <div class="row">
                      <div class="col-12 col-sm-auto mb-3">
                        <div class="mx-auto" style="width: 140px">
                          <div
                            class="d-flex justify-content-center align-items-center rounded"
                            style="
                              height: 140px;
                              background-color: rgb(233, 236, 239);
                            "
                          >
                            <span
                              style="
                                color: rgb(166, 168, 170);
                                font: bold 8pt Arial;
                              "
                              >140x140</span
                            >
                          </div>
                        </div>
                      </div>
                      <div
                        class="col d-flex flex-column flex-sm-row justify-content-between mb-3"
                      >
                        <div class="text-center text-sm-left mb-2 mb-sm-0">
                          <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">
                              ${detail.fullName}
                          </h4>
                          <p class="mb-0">${detail.user}</p>
                          
                          <div class="mt-2">
                            <button class="btn btn-primary" type="button">
                              <i class="fa fa-fw fa-camera"></i>
                              <span>Change Photo</span>
                            </button>
                          </div>
                        </div>
                        <div class="text-center text-sm-right">
                          <span class="badge badge-secondary"
                            >${detail.roleName}</span
                          >
                          <div class="text-muted">
                            <small>Joined ${detail.dateEnroll}</small>
                          </div>
                        </div>
                      </div>
                    </div>
                    <ul class="nav nav-tabs">
                      <li class="nav-item">
                        <a href class="active nav-link">Settings</a>
                      </li>
                    </ul>
                    <div class="tab-content pt-3">
                      <div class="tab-pane active">
                        <form action="EditUser" class="form" novalidate>
                          <div class="row">
                            <div class="col">
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Full Name</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="fullName"
                                      placeholder="John Smith"
                                      value="${detail.fullName}"
                                    />
                                  </div>
                                </div>
                                <div class="col">
                                  <div class="form-group">
                                    <label>Username</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="user"
                                      placeholder="johnny.s"
                                      value="${detail.user}"
                                      readonly
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Role</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="roleName"
                                      placeholder="User"
                                     value="${detail.roleName}"
                                      
                                    />
                                  </div>
                                </div>
                                <div class="col">
                                  <div class="form-group">
                                    <label>Status</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="activate"
                                      placeholder="Active"
                                      value="${detail.activate}"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Mobile</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="mobile"
                                      placeholder="(0)98********"
                                      value="${detail.mobile}"
                                    />
                                  </div>
                                </div>
                                <div class="col">
                                  <div class="form-group">
                                    <label>Date Of Birth</label>
                                    <input
                                      class="form-control"
                                      type="date"
                                      name="dob"
                                      placeholder="dob"
                                      value="${detail.dob}"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Gender</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="g"
                                      placeholder="Male"
                                      value="${detail.g}"
                                    />
                                  </div>
                                </div>
                                <div class="col">
                                  <div class="form-group">
                                    <label>Enrolled Date</label>
                                    <input
                                      class="form-control"
                                      type="date"
                                      name="dateEnroll"
                                      placeholder="23/09/2023"
                                      value="${detail.dateEnroll}"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Email</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      name="email"
                                      placeholder="user@example.com"
                                      value="${detail.email}"
                                    />
                                  </div>
                                </div>
                                <div class="col">
                                  <div class="form-group">
                                    <label>Password</label>
                                    <input
                                      class="form-control"
                                      type="password"
                                      name="pass"
                                      placeholder="??????"
                                      value="${detail.pass}"
                                    />
                                  </div>
                                </div>
                              </div>
                              
                            </div>
                          </div>
                          <div class="row">
                            <div class="col d-flex justify-content-end">
                              <button class="btn btn-primary" type="submit">
                                Save Changes
                              </button>
                            </div>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-12 col-md-3 mb-3">
              <div class="card mb-3">
                <div class="card-body">
                  <div class="px-xl-3">
                    <button class="btn btn-block btn-secondary">
                      <i class="fa fa-sign-out"></i>
                      <span>Logout</span>
                    </button>
                  </div>
                </div>
              </div>
              <div class="card">
                <div class="card-body">
                  <h6 class="card-title font-weight-bold">Support</h6>
                  <p class="card-text">
                    Get fast, free help from our friendly assistants.
                  </p>
                  <button type="button" class="btn btn-primary">
                    Contact Us
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
  </body>
</html>
