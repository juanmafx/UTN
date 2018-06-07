using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    class Alumno : Persona
    {
        private int legajo;
        private double nota1;
        private double nota2;
        private double nota3;

        public int Legajo
        {
            get
            {
                return legajo;
            }

            set
            {
                legajo = value;
            }
        }

        public double Nota1
        {
            get
            {
                return nota1;
            }

            set
            {
                nota1 = value;
            }
        }

        public double Nota2
        {
            get
            {
                return nota2;
            }

            set
            {
                nota2 = value;
            }
        }

        public double Nota3
        {
            get
            {
                return nota3;
            }

            set
            {
                nota3 = value;
            }
        }

        public Alumno(int legajo, double nota1, double nota2, double nota3, string nombre, string apellido, int tipoDocumento, int documento, bool sexo, DateTime fn) :base(nombre,apellido,tipoDocumento,documento,sexo,fn)
        {
            this.Legajo = legajo;
            this.Nota1 = nota1;
            this.Nota2 = nota2;
            this.Nota3 = Nota3;

        }

        public Alumno():base()
        {
            this.Legajo = 0;
            this.Nota1 = 0;
            this.Nota2 = 0;
            this.Nota3 = 0;
            //base.pNombre = "";
        }

        //Calcular Promedio 

        public string toStringAlumno()
        {
            return legajo + base.toStringPersona () + Nota1 + nota2 + nota3;

        }
        
    }
}
