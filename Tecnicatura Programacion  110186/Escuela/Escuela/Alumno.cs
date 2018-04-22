using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Escuela
{
    class Alumno
    {
        private int legajo;
        private string nombre;
        private double n1;
        private double n2;
        private double n3;

        public int pLegajo
        {
            set { legajo = value; }
            get { return legajo; }
        }

        public string pNombre
        {
            set { nombre = value; }
            get { return nombre; }
        }

        public double pN1
        {
            set { n1 = value; }
            get { return n1; }
        }

        public double pN2
        {
            set { n2 = value; }
            get { return n2; }
        }

        public double pN3
        {
            set { n3 = value; }
            get { return n3; }
        }

        public double calProm()
        {
            double p;
            p = (n1 + n2 + n3) / 3;
            return p;
        }

        public Alumno()
        {
            legajo = 0;
            nombre = "";
            n1 = 0;
            n2 = 0;
            n3 = 0;
        }

        public Alumno(int leg)
        {
            legajo = leg;
            nombre = "";
            n1 = n2 = n3 = 0;

        }

        public Alumno(int leg, string nombre, double n1, double n2, double n3)
        {
            legajo = leg;
            nombre = "";
            n1 = n2 = n3 = 0;
        }

        public string toString()
        {
            return "legajo: " + legajo + "\n" +
            "Nombre: " + nombre + "\n" +
            "Nota 1: " + n1 + "\n" +
            "Nota 2: " + n2 + "\n" +
            "Nota 3: " + n3 + "\n" +
            "Legajo: " + legajo;
        }




    }
}
