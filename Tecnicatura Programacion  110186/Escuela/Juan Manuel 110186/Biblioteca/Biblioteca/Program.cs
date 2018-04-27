using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Biblioteca
{
    class Program
    {
        static void Main(string[] args)
        {

                Socio a;
                a = new Socio();

                Console.WriteLine("Ingrese Numero de Socio :");
                a.pNumero = Convert.ToInt16(Console.ReadLine());

                Console.WriteLine("Ingrese Nombre :");
                a.pNombre = Console.ReadLine();

                Console.WriteLine("Ingrese ANIO de Nacimiento :");
                a.pFnYear = Convert.ToInt16(Console.ReadLine());

                Console.WriteLine("Ingrese MES de Naciemito : ");
                a.pFmonth = Convert.ToInt16(Console.ReadLine());

                Console.WriteLine("Ingrese DIA de Naciemito :");
                a.pFday = Convert.ToInt16(Console.ReadLine());

                Console.WriteLine("- - - - - - - - - - - - - - - -  - - - - - -  ");

                Console.WriteLine(a.toString());

                Console.WriteLine("- - - - - - - - - - - - - - - - - - - - -  -");

                Console.ReadKey();


        }
    }
}
