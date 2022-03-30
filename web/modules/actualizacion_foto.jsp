<%-- 
    Document   : actualizar_foto
    Created on : 30/03/2022, 11:28:18 AM
    Author     : Leonardo
--%>
<link rel="stylesheet" href="../css/style-set-foto.css">

<hr>
<form  name="formPhoto" action="../UpdatePhoto.do" method="post" enctype="multipart/form-data"> 
    <div id="div-foto-perfil" class="form-row">
        <div class="form-group col-md-4">                                    
            <div class="profile-photo-div" id="profile-photo-div">
                <div class="profile-img-div" id="profile-img-div">
                    <div id="loader"></div><img id="profile-img" src="<%=user.get("urlFoto")%>"/>
                    <input id="x-position" type="range" name="x-position" value="0" min="0"/>
                    <input id="y-position" type="range" name="y-position" value="0" min="0"/>
                </div>
                <div class="profile-buttons-div">
                    <div class="profile-img-input" id="profile-img-input">
                        <label class="button-pic" id="change-photo-label" for="change-photo">Cambiar foto</label>
                        <input id="change-photo" name="change-photo" type="file" style="display: none" accept="image/*"/>
                    </div>
                    <div class="profile-img-confirm" id="profile-img-confirm" style="display: none">
                        <div class="button-pic half green" id="save-img"><i class="fa fa-check" aria-hidden="true"></i></div>
                        <div class="button-pic half red" id="cancel-img"><i class="fa fa-times" aria-hidden="true"></i></div>
                    </div>
                </div>
            </div>
            <div class="error" id="error">min sizes 400*400px</div>
            <canvas id="croppedPhoto" width="400" height="400"></canvas>
        </div>
    </div>
</form>
<hr>
                    
<script src="../js/set-foto-scripts.js"></script>