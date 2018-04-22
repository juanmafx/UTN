using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Escuela
{
    class Program
    {
        static void Main(string[] args)
        {
            Alumno a;
            a = new Alumno();

            Console.WriteLine("Ingrese Legajo");
            a.pLegajo = Convert.ToInt16(Console.ReadLine());

            Console.WriteLine("Ingrese Nombre");
            a.pNombre = Console.ReadLine();

            Console.WriteLine("Ingrese nota 1");
            a.pN1 = Convert.ToInt16(Console.ReadLine());

            Console.WriteLine("Ingrese nota 2");

            a.pN2 = Convert.ToInt16(Console.ReadLine());

            Console.WriteLine("Ingrese nota 3");
            a.pN3 = Convert.ToInt16(Console.ReadLine());

            
            Console.WriteLine("El promedio es "+a.calProm());
            Console.ReadKey();

        }
    }
}
