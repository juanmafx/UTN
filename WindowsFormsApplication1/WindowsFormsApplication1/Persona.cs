using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    abstract class Persona
    {
        private string nombre;
        private string apellido;
        private int tipoDocumento;
        private int documento;
        private bool sexo;
        private DateTime fn;

         public Persona(string nombre, string apellido, int tipoDocumento, int documento, bool sexo, DateTime fn)
        {
            this.nombre = nombre;
            this.apellido = apellido;
            this.tipoDocumento = tipoDocumento;
            this.documento = documento;
            this.sexo = sexo;
            this.fn = fn;
        }

        public Persona()
        {
            this.nombre = "";
            this.apellido = "";
            this.tipoDocumento =0;
            this.documento = 0;
            this.sexo = false;
            this.fn = DateTime.Today;
        }

        public string pNombre
        {
            get
            {
                return nombre;
            }

            set
            {
                nombre = value;
            }
        }

        public string pApellido
        {
            get
            {
                return apellido;
            }

            set
            {
                apellido = value;
            }
        }

        public int pTipoDocumento
        {
            get
            {
                return tipoDocumento;
            }

            set
            {
                tipoDocumento = value;
            }
        }

        public int pDocumento
        {
            get
            {
                return documento;
            }

            set
            {
                documento = value;
            }
        }

        public bool pSexo
        {
            get
            {
                return sexo;
            }

            set
            {
                sexo = value;
            }
        }

        public DateTime pFn
        {
            get
            {
                return fn;
            }

            set
            {
                fn = value;
            }
        }

        public string toStringPersona()
        {
            return nombre + apellido ;

        }
    }
}
