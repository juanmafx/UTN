using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class txtn2 : Form
    {
        public txtn2()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void textBox6_TextChanged(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            alumno a;
            a = new alumno();
            a.pLeg = Convert.ToInt32(textleg.Text);
            a.pNombre = Convert.ToString(textnombre.Text);
            a.pN1 = Convert.ToInt32(textn1.Text);
            a.pN2 = Convert.ToInt32(textn2.Text);
            a.pN3 = Convert.ToInt32(textn3.Text);
            laprom.Text = a.prom().ToString("0.000");





        }

        private void label8_Click(object sender, EventArgs e)
        {

        }
    }
}
