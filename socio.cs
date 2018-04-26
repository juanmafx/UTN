using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Biblioteca
{
    class Socio
    {

        private int numero;
        private string nombre;
        private int fnYear;
        private int fnMonth;
        private int fnDay;

        public int pNumero
        {
            set { numero = value; }
            get { return numero; }
        }

        public string pNombre
        {
            set { nombre = value; }
            get { return nombre; }
        }

        public int pFnYear
        {
            set { fnYear = value; }
            get { return fnYear; }
        }

        public int pFmonth
        {
            set { fnMonth = value; }
            get { return fnMonth; }
        }

        public int pFday
        {
            set { fnDay = value; }
            get { return fnDay; }
        }



        public double calEdad()
        {
            var today = DateTime.Today;
            var a = (today.Year * 100 + today.Month) * 100 + today.Day;
            var b = (fnYear * 100 + fnMonth) * 100 + fnDay;
            return (a - b) / 10000;
        }

        public Socio()
        {
            numero = 0;
            nombre = "";

        }

        public Socio(int leg)
        {
            nombre = "";
                    }

        public Socio(int numero, string nombre, int fnYear, int fnMonth, int fnDay)
        {
            numero = 0;
            nombre = "";
            fnYear = fnMonth = fnDay = 0;
        }

        public string toString()
        {
            return
                "Nun Soc   : " + numero + "\n" +
                "Nombre    : " + nombre + "\n" +
                "Nacido el : "+ fnYear+ "/" + fnMonth + "/" + fnDay + "\n" +
                "La EDAD de : "+ nombre + " al "+ DateTime.Now + "  es de = " + calEdad() ;
        }


    }
}
