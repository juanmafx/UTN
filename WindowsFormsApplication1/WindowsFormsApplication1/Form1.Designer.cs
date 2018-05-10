namespace WindowsFormsApplication1
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.Legajo = new System.Windows.Forms.Label();
            this.leg = new System.Windows.Forms.TextBox();
            this.sexo = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.horasTrabajadas = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.precioHora = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.ts = new System.Windows.Forms.Label();
            this.nombre = new System.Windows.Forms.TextBox();
            this.Label = new System.Windows.Forms.Label();
            this.masculino = new System.Windows.Forms.RadioButton();
            this.femenino = new System.Windows.Forms.RadioButton();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(339, 394);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(323, 66);
            this.button1.TabIndex = 5;
            this.button1.Text = "CALCULAR";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Legajo
            // 
            this.Legajo.AutoSize = true;
            this.Legajo.Location = new System.Drawing.Point(481, 26);
            this.Legajo.Name = "Legajo";
            this.Legajo.Size = new System.Drawing.Size(39, 13);
            this.Legajo.TabIndex = 1;
            this.Legajo.Text = "Legajo";
            this.Legajo.Click += new System.EventHandler(this.label1_Click);
            // 
            // leg
            // 
            this.leg.Location = new System.Drawing.Point(446, 57);
            this.leg.Name = "leg";
            this.leg.Size = new System.Drawing.Size(100, 20);
            this.leg.TabIndex = 0;
            this.leg.TextChanged += new System.EventHandler(this.leg_TextChanged);
            // 
            // sexo
            // 
            this.sexo.Location = new System.Drawing.Point(446, 192);
            this.sexo.Name = "sexo";
            this.sexo.Size = new System.Drawing.Size(100, 20);
            this.sexo.TabIndex = 2;
            this.sexo.TextChanged += new System.EventHandler(this.sexo_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(485, 161);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(31, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Sexo";
            // 
            // horasTrabajadas
            // 
            this.horasTrabajadas.Location = new System.Drawing.Point(446, 270);
            this.horasTrabajadas.Name = "horasTrabajadas";
            this.horasTrabajadas.Size = new System.Drawing.Size(100, 20);
            this.horasTrabajadas.TabIndex = 3;
            this.horasTrabajadas.TextChanged += new System.EventHandler(this.horasTrabajadas_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(455, 240);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(91, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Horas Trabajadas";
            // 
            // precioHora
            // 
            this.precioHora.Location = new System.Drawing.Point(446, 344);
            this.precioHora.Name = "precioHora";
            this.precioHora.Size = new System.Drawing.Size(100, 20);
            this.precioHora.TabIndex = 4;
            this.precioHora.TextChanged += new System.EventHandler(this.precioHora_TextChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(469, 312);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(63, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Precio Hora";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 28F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(721, 57);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(369, 44);
            this.label1.TabIndex = 9;
            this.label1.Text = "TOTAL A COBRAR :";
            this.label1.Click += new System.EventHandler(this.label1_Click_1);
            // 
            // ts
            // 
            this.ts.AutoSize = true;
            this.ts.Location = new System.Drawing.Point(874, 146);
            this.ts.Name = "ts";
            this.ts.Size = new System.Drawing.Size(0, 13);
            this.ts.TabIndex = 10;
            this.ts.Click += new System.EventHandler(this.label5_Click);
            // 
            // nombre
            // 
            this.nombre.Location = new System.Drawing.Point(442, 120);
            this.nombre.Name = "nombre";
            this.nombre.Size = new System.Drawing.Size(100, 20);
            this.nombre.TabIndex = 1;
            this.nombre.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // Label
            // 
            this.Label.AutoSize = true;
            this.Label.Location = new System.Drawing.Point(481, 89);
            this.Label.Name = "Label";
            this.Label.Size = new System.Drawing.Size(44, 13);
            this.Label.TabIndex = 11;
            this.Label.Text = "Nombre";
            this.Label.Click += new System.EventHandler(this.label6_Click);
            // 
            // masculino
            // 
            this.masculino.AutoSize = true;
            this.masculino.Location = new System.Drawing.Point(587, 192);
            this.masculino.Name = "masculino";
            this.masculino.Size = new System.Drawing.Size(73, 17);
            this.masculino.TabIndex = 12;
            this.masculino.TabStop = true;
            this.masculino.Text = "Masculino";
            this.masculino.UseVisualStyleBackColor = true;
            // 
            // femenino
            // 
            this.femenino.AutoSize = true;
            this.femenino.Location = new System.Drawing.Point(678, 192);
            this.femenino.Name = "femenino";
            this.femenino.Size = new System.Drawing.Size(71, 17);
            this.femenino.TabIndex = 13;
            this.femenino.TabStop = true;
            this.femenino.Text = "Femenino";
            this.femenino.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1323, 556);
            this.Controls.Add(this.femenino);
            this.Controls.Add(this.masculino);
            this.Controls.Add(this.nombre);
            this.Controls.Add(this.Label);
            this.Controls.Add(this.ts);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.precioHora);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.horasTrabajadas);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.sexo);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.leg);
            this.Controls.Add(this.Legajo);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label Legajo;
        private System.Windows.Forms.TextBox leg;
        private System.Windows.Forms.TextBox sexo;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox horasTrabajadas;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox precioHora;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label ts;
        private System.Windows.Forms.TextBox nombre;
        private System.Windows.Forms.Label Label;
        private System.Windows.Forms.RadioButton masculino;
        private System.Windows.Forms.RadioButton femenino;
    }
}

