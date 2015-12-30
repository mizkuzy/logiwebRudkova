<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            $("#select1").change(function () {
                var x = $('select option:selected').attr('name');
                $('#step2').find('select').css('display', 'none');
                $('#' + x).css('display', 'block');
            })
        });
    </script>
    <style>
        #step2 select {
            display: none;
        }
    </style>
    <title>New request - LOGIWEB</title>

</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<h1>Create new request</h1>

<div>
    <form role="form" id="form_new_request" action="addNewRequest" method="post">
        Goods<input name="goods_name" type="text" required><br>
        Mass<input name="mass" type="text" required><br>
        City from
        <select id="select1" name="city1" required>
            <option selected disabled>Choose</option>
            <option name="Saint-Petersburg">Saint-Petersburg</option>
            <option name="Veliky_Novgorod">Veliky_Novgorod</option>
            <option name="Pskov">Pskov</option>
            <option name="Petrozavodsk">Petrozavodsk</option>
            <option name="Arhangelsk">Arhangelsk</option>
            <option name="Vologda">Vologda</option>
            <option name="Siktivkar">Siktivkar</option>
            <option name="Naryan-Mar">Naryan-Mar</option>
            <option name="Murmansk">Murmansk</option>
            <option name="Kaliningrad">Kaliningrad</option>
        </select>

        <div id="step2">
            City to
            <select id="Saint-Petersburg" name="city2" required>

                <option selected disabled>Choose</option>
                <option name="Veliky_Novgorod">Veliky_Novgorod</option>
                <option name="Pskov">Pskov</option>
                <option name="Petrozavodsk">Petrozavodsk</option>
                <option name="Arhangelsk">Arhangelsk</option>
                <option name="Vologda">Vologda</option>
                <option name="Siktivkar">Siktivkar</option>
                <option name="Naryan-Mar">Naryan-Mar</option>
                <option name="Murmansk">Murmansk</option>
                <option name="Kaliningrad">Kaliningrad</option>
            </select>
            <select id="Veliky_Novgorod" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Pskov">Pskov</option>
                <option name="Petrozavodsk">Kaliningrad</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Pskov" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Veliky_Novgorod">Veliky_Novgorod</option>
                <option name="Petrozavodsk">Kaliningrad</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Petrozavodsk" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Murmansk">Murmansk</option>
                <option name="Arhangelsk">Arhangelsk</option>
                <option name="Naryan-Mar">Naryan-Mar</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Arhangelsk" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Naryan-Mar">Naryan-Mar</option>
                <option name="Petrozavodsk">Petrozavodsk</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Vologda" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Siktivkar">Siktivkar</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Siktivkar" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Vologda">Vologda</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Naryan-Mar" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Arhangelsk">Arhangelsk</option>
                <option name="Petrozavodsk">Petrozavodsk</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Murmansk" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Petrozavodsk">Petrozavodsk</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
            <select id="Kaliningrad" name="city2" required>
                <option selected disabled>Choose</option>
                <option name="Veliky_Novgorod">Veliky_Novgorod</option>
                <option name="Pskov">Pskov</option>
                <option name="Saint-Petersburg">Saint-Petersburg</option>
            </select>
        </div>

        <br>
        <input type="submit" value="ADD">
    </form>
</div>

</body>
</html>