import { z } from "zod";

export const formSchema = z.object({
  name: z.string().min(3, "Nome é obrigatório"),
  warName: z.string().min(2, "Nome de guerra é obrigatório"),
  rank: z.enum(
    [
      "Sd EV",
      "Sd EP",
      "Cb",
      "3º Sgt",
      "2º Sgt",
      "1º Sgt",
      "S Ten",
      "Asp",
      "2º Ten",
      "1º Ten",
      "Cap",
      "Maj",
      "Ten Cel",
      "Cel",
    ],
    { required_error: "Selecione um posto/graduação" }
  ),
  email: z.string().email("E-mail inválido"),
  phoneNumber: z.string().min(10, "Telefone inválido"),
  cpf: z.string().min(11, "CPF inválido"),
  company: z.string().min(3, "OM/SU é obrigatório"),
  model: z.string().min(2, "Modelo do veículo é obrigatório"),
  plate: z.string().min(6, "Placa do veículo inválida"),
  color: z.enum(
    [
      "vermelho",
      "preto",
      "branco",
      "azul",
      "cinza",
      "prata",
      "verde",
      "amarelo",
    ],
    { required_error: "Selecione uma cor" }
  ),
  type: z.enum(["car", "motorcycle"], {
    required_error: "Escolha o tipo do veículo",
  }),
  licensing: z.string({
    required_error: "Informe o licenciamento do veículo",
  }),
  driverLicenseExpiration: z.date({
    required_error: "Escolha a data de vencimento da CNH",
  }),
  cnh: z.any(),
  crlv: z.any(),
});

export type FormData = z.infer<typeof formSchema>;
