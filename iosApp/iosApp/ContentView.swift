import SwiftUI
import shared

struct ContentView: View {
    @State private var peso = ""
    @State private var altura = ""
    @State private var resultado = "Informe os valores"

    var body: some View {
        VStack(spacing: 20) {
            // Topo com fundo degradê
            ZStack {
                LinearGradient(gradient: Gradient(colors: [Color.cyan, Color.blue]),
                               startPoint: .topLeading, endPoint: .bottomTrailing)
                    .frame(height: 400)
                    .cornerRadius(50, corners: [.bottomLeft, .bottomRight])
                    .edgesIgnoringSafeArea(.top)

                VStack(spacing: 16) {
                    Image(systemName: "dumbbell.fill") // Ícone de peso
                        .resizable()
                        .scaledToFit()
                        .frame(width: 60, height: 60)
                        .foregroundColor(.white)
                        .padding(.top, 50)

                    Text("Calcule seu IMC abaixo:")
                        .foregroundColor(.white)
                        .font(.largeTitle)
                        .bold()
                }
            }
            .frame(height: 300)

            Spacer()

            VStack(spacing: 16) {
                // Campo Peso
                HStack {
                    Image(systemName: "scalemass")
                        .resizable()
                        .frame(width: 30, height: 30)
                    TextField("Digite seu peso atual (kg)", text: $peso)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .keyboardType(.decimalPad)
                        .padding(.horizontal)
                }

                // Campo Altura
                HStack {
                    Image(systemName: "arrow.up.and.down.circle")
                        .resizable()
                        .frame(width: 30, height: 30)
                    TextField("Digite sua altura (m)", text: $altura)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .keyboardType(.decimalPad)
                        .padding(.horizontal)
                }

                // Botão Calcular IMC
                Button(action: {
                    if let pesoDouble = Double(peso),
                       let alturaDouble = Double(altura) {
                        let imc = IMCCalculator().calcularIMC(peso: pesoDouble, altura: alturaDouble)
                        let interpretacao = IMCCalculator().interpretarIMC(imc: imc)
                        resultado = "IMC: \(String(format: "%.2f", imc)) - \(interpretacao)"
                    } else {
                        resultado = "Preencha os campos corretamente!"
                    }
                }) {
                    Text("Calcular IMC")
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.blue)
                        .cornerRadius(8)
                }
                .padding(.horizontal)

                // Resultado
                Text(resultado)
                    .font(.title2)
                    .bold()
                    .padding(.top, 20)
            }
            .padding()

            Spacer()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

// Extensão para arredondar apenas as bordas inferiores
extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape(RoundedCorner(radius: radius, corners: corners))
    }
}

struct RoundedCorner: Shape {
    var radius: CGFloat
    var corners: UIRectCorner

    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        return Path(path.cgPath)
    }
}
