using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    class alumno
    {
        private int leg;
        private string nombre;
        private int n1;
        private int n2;
        private int n3;

        public int pLeg
        {
            set { leg = value; }
            get { return leg; }
        }

        public string pNombre
        {
            set { nombre = value; }
            get { return nombre; }
        }



        public int pN1
        {
            set { n1 = value; }
            get { return n2; }
        }

        public int pN2
        {
            set { n2 = value; }
            get { return n2; }
        }
        public int pN3
        {
            set { n3 = value; }
            get { return n3; }
        }



        public double prom()
        {
            var a = (n1+n2+n3)/3;
            return a;
        }

        public alumno()
        {
            leg = 0;
            nombre = "";

        }

        public alumno(int leg)
        {
            nombre = "";
        }

        public alumno(int leg, string nombre, int n1, int n2, int n3)
        {
            leg = 0;
            nombre = "";
            n1 = n2 = n3 = 0;
        }

        public string toString()
        {
            return
                "Legajo   : " + leg + "\n" +
                "Nombre    : " + nombre + "\n" +
                "NOTAS: " + n1 + "/" + n2 + "/" + n3 + "\n" +
                "El promedio de  : " + nombre + " es "  + prom();
        }


    }
}
