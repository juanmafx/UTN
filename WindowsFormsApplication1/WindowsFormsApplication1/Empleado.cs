using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    class Empleado
    {
        private int leg;
        private string nombre;
        private bool sexo;
        private float horasTrabajadas;
        private float precioHora;

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

        public bool pSexo
        {
            set { sexo = value; }
            get { return sexo; }
        }

        public float pHorasTrabajadas
        {
            set { horasTrabajadas = value; }
            get { return horasTrabajadas; }
        }

        public float pPrecioHoras
        {
            set { precioHora = value; }
            get { return precioHora; }
        }

        public float calSueldo()
        {
            var a = horasTrabajadas * precioHora *4;
            return a;
        }


        public string toString()
        {
            return
                "Legajo   : " + leg + "\n" +
                "Nombre    : " + nombre + "\n" +
                "Sexo: " + sexo +"\n" +
                "Sueldo : " + calSueldo();
        }

        public Empleado(){
        leg = 0;
        nombre = "";
        sexo = true ;
        horasTrabajadas = 0;
        precioHora=0;

    }



    }
}
