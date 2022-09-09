package com.example.edu.huellitas.Modelos;

public class Conexion {

    public String ConecBase()
    {
        return "http://192.168.18.2:8087/HuellitasSV/rest/";
    }

    //USUARIO
    public String LoginU()
    {
        return "usuario/login";
    }

    public String RegistrarU()
    {
        return "usuario/registrar";
    }

    public String PerfilU()
    {
        return "usuario/perfil";
    }

    public String PerfilP()
    {
        return "usuario/perfilP";
    }

    public String PerfilA()
    {
        return "usuario/perfilA";
    }


    //RESERVA
    public String ReservarPaseo()
    {
        return "paseo/reservar";
    }

    public String ReservasPendientes()
    {
        return "paseo/estadoPendiente";
    }

    public String RealizarReserva()
    {
        return "paseo/realizar";
    }

    public String ReservaEnCurso()
    {
        return "paseo/estadoEnCurso";
    }

    public String DetalleReserva()
    {
        return "paseo/detalleReserva";
    }

    public String FinalizarReserva()
    {
        return "paseo/finalizar";
    }

    public String ReservaRealizadas()
    {
        return "paseo/estadoRealizados";
    }

    public String PaseosPendientes()
    {
        return "paseo/listarPP";
    }

    public String HistorialReserva()
    {
        return "paseo/listarHR";
    }

    //TARJETA
    public String RegistrarTarjeta()
    {
        return "tarjeta/registrar";
    }

    public String ListarTarjeta()
    {
        return "tarjeta/listar";
    }

    //RECLAMO
    public String RegistrarReclamo(){
        return "reclamo/registrar";
    }

    public String ListarReclamoCli(){
        return "reclamo/listarCli";
    }

    public String ListarReclamoPetw(){
        return "reclamo/listarPetw";
    }


    //DASHBOARDS
    public String DashboardUsuario(){
        return "dbu";
    }

    public String DashboardReserva(){
        return "dbres";
    }

    public String DashboardReclamo(){
        return "dbrcl";
    }

    //BACKUP

    public String BackupBD(){
        return "backup";
    }


    //CRUDS

    public String ListarCliente(){
        return "usuario/listadoCLI";
    }

    public String RegistrarCliente(){
        return "usuario/registrarCLI";
    }

    public String ActualizarCliente(){
        return "usuario/actualizarCLI";
    }

    public String EliminarCliente(){
        return "usuario/eliminarCLI";
    }




    public String ListarPetwalker(){
        return "usuario/listadoPETW";
    }

    public String RegistrarPetwalker(){
        return "usuario/registrarPETW";
    }

    public String ActualizarPetwalker(){
        return "usuario/actualizarPETW";
    }

    public String EliminarPetwalker(){
        return "usuario/eliminarPETW";
    }




    public String ListarReclamos(){
        return "reclamo/listado";
    }

    public String ActualizarReclamo(){
        return "reclamo/actualizar";
    }




    public String ListarReserva(){
        return "paseo/listado";
    }

    public String ActualizarReserva(){
        return "paseo/actualizar";
    }

}
