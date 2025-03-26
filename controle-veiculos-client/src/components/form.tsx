"use client";

import { FormInput } from "@/components/input";
import { FormRadioGroup } from "@/components/radio-group";
import { Select } from "@/components/select";
import { Button } from "@/components/ui/button";
import { DatePickerDemo } from "@/components/ui/date-picker";
import { formSchema, type FormData } from "@/schemas/form-schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { Controller, FormProvider, useForm } from "react-hook-form";

import { XCircle } from "lucide-react";
import { useRef, useState, type ChangeEvent } from "react";
import situacaoVeiculoPDF from "../../public/cadastramento-veiculos.pdf";
import declaracaoPDF from "../../public/declaracao-cia.pdf";
import { Separator } from "./ui/separator";
import { Input } from "./ui/input";

export const RegistrationForm = () => {
  const fileArr = [] as File[];

  const [docs, setDocs] = useState<File[]>([]);

  const methods = useForm<FormData>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: "",
      warName: "",
      rank: undefined,
      email: "",
      phoneNumber: "",
      cpf: "",
      company: "",
      model: "",
      plate: "",
      color: undefined,
      type: undefined,
      licensing: undefined,
      driverLicenseExpiration: undefined,
    },
  });

  const onSubmit = (data: FormData) => {
    console.log(data);
  };

  const refFile = useRef<HTMLInputElement>(null);

  const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const { files } = e.target;

      Array.prototype.forEach.call(files, function (file) {
        fileArr.push(file);
      });
      setDocs(fileArr);
    }
  };

  const removeFile = (index: number) => {
    setDocs([...docs.slice(0, index), ...docs.slice(index + 1)]);
  };

  return (
    <FormProvider {...methods}>
      <div className="flex flex-col gap-y-2 mb-8">
        <h1 className="font-semibold text-2xl text-card-foreground">
          Cadastro de Veículos
        </h1>
        <h4 className="font-normal text-xs text-card-foreground">
          Preencha os campos abaixo para cadastrar um veículo e receber o selo
          do estacionamento.
        </h4>
      </div>
      <form
        onSubmit={methods.handleSubmit(onSubmit)}
        className="flex items-start w-full h-full justify-between"
      >
        <div className="space-y-4 w-2/5">
          <FormInput
            control={methods.control}
            name="name"
            label="Nome completo"
          />
          <FormInput
            control={methods.control}
            name="warName"
            label="Nome de guerra"
          />

          <Controller
            control={methods.control}
            name="rank"
            render={({ field }) => (
              <Select
                label="Posto/Graduação"
                options={[
                  { label: "Sd EV", value: "Sd EV" },
                  { label: "Sd EP", value: "Sd EP" },
                  { label: "Cb", value: "Cb" },
                  { label: "3º Sgt", value: "3º Sgt" },
                  { label: "2º Sgt", value: "2º Sgt" },
                  { label: "1º Sgt", value: "1º Sgt" },
                  { label: "S Ten", value: "S Ten" },
                  { label: "Asp", value: "Asp" },
                  { label: "2º Ten", value: "2º Ten" },
                  { label: "1º Ten", value: "1º Ten" },
                  { label: "Cap", value: "Cap" },
                  { label: "Maj", value: "Maj" },
                  { label: "Ten Cel", value: "Ten Cel" },
                  { label: "Cel", value: "Cel" },
                ]}
                value={field.value}
                onChange={field.onChange}
                onBlur={field.onBlur}
              />
            )}
          />

          <FormInput
            control={methods.control}
            name="email"
            label="E-mail"
            type="email"
          />
          <FormInput
            control={methods.control}
            name="phoneNumber"
            label="Telefone/Celular"
          />
          <FormInput control={methods.control} name="cpf" label="CPF" />

          <Controller
            control={methods.control}
            name="company"
            render={({ field }) => (
              <Select
                label="OM/SU"
                options={[
                  { label: "6º BI Amv", value: "6BIAMV" },
                  { label: "12º Cia Com Amv", value: "12CIACOM" },
                  { label: "12º Pel Pe Amv", value: "12PELPE" },
                  { label: "Cia Cmdo", value: "CIACMDO" },
                  { label: "12º Bda Amv", value: "12BDAAMV" },
                ]}
                value={field.value}
                onChange={field.onChange}
                onBlur={field.onBlur}
              />
            )}
          />

          <FormInput
            control={methods.control}
            name="model"
            label="Modelo do veículo"
          />
          <FormInput control={methods.control} name="plate" label="Placa" />

          <Controller
            control={methods.control}
            name="color"
            render={({ field }) => (
              <Select
                label="Cor do veículo"
                options={[
                  { label: "Vermelho", value: "vermelho" },
                  { label: "Preto", value: "preto" },
                  { label: "Branco", value: "branco" },
                  { label: "Azul", value: "azul" },
                  { label: "Cinza", value: "cinza" },
                  { label: "Prata", value: "prata" },
                  { label: "Verde", value: "verde" },
                  { label: "Amarelo", value: "amarelo" },
                ]}
                value={field.value}
                onChange={field.onChange}
                onBlur={field.onBlur}
              />
            )}
          />

          <Controller
            control={methods.control}
            name="type"
            render={({ field }) => (
              <FormRadioGroup
                control={methods.control}
                name="type"
                label="Tipo de veículo"
                options={[
                  { label: "Carro", value: "car" },
                  { label: "Moto", value: "motorcycle" },
                ]}
                value={field.value}
                onChange={field.onChange}
                onBlur={field.onBlur}
              />
            )}
          />

          <Controller
            control={methods.control}
            name="licensing"
            render={({ field }) => (
              <DatePickerDemo
                label="Licenciamento do veículo"
                value={field.value}
                onChange={field.onChange}
              />
            )}
          />

          <Controller
            control={methods.control}
            name="driverLicenseExpiration"
            render={({ field }) => (
              <DatePickerDemo
                label="Vencimento da CNH"
                value={field.value}
                onChange={field.onChange}
              />
            )}
          />

          <div className="flex flex-col gap-y-2 mt-4">
            <span className="text-xs font-normal text-card-foreground">
              Obs: Este selo só será recebido mediante assinatura dos
              responsáveis pela 2ª Seção. O selo só será validado com carimbo e
              a assinatura dos mesmos.
            </span>
            <span className="text-xs font-normal text-card-foreground">
              Ao enviar este formulário, declaro ter ciência dos termos acima
              descritos e das normas de estacionamento do Forte Ipiranga.
            </span>
          </div>
        </div>

        <div className="flex flex-col w-2/5">
          <div className="flex flex-col gap-y-2 mb-6">
            <h1 className="font-semibold text-2xl text-card-foreground">
              Envio dos documentos
            </h1>
            <h4 className="font-normal text-xs text-card-foreground">
              Verifique no documento abaixo sua situação e quais documentos deve
              enviar.
            </h4>
          </div>

          <a
            href={situacaoVeiculoPDF}
            target="_blank"
            rel="noopener noreferrer"
            className="max-w-fit bg-accent/85 px-3 py-1 rounded-md text-white text-xs font-open font-medium hover:bg-accent duration-200"
          >
            ACESSAR DOCUMENTO
          </a>

          <Separator className="my-6" />

          <div className="flex flex-col gap-y-2 mt-4">
            <span className="text-xs font-normal text-card-foreground">
              Se necessário, realize o download do formulário anexado abaixo.
            </span>

            <a
              href={declaracaoPDF}
              target="_blank"
              rel="noopener noreferrer"
              className="max-w-fit bg-accent/85 px-3 py-1 rounded-md text-white text-xs font-open font-medium hover:bg-accent duration-200"
            >
              DECLARAÇÃO DE USO DO VEÍCULO
            </a>

            <span className="text-xs font-normal text-card-foreground mt-4">
              Após preencher e reconhecer firma em cartório, envie em anexo o
              formulário escaneado, no formato .PDF{" "}
            </span>

            <Separator className="my-6" />

            <div className="mb-12 mt-8">
              <Button type="button" onClick={() => refFile.current?.click()}>
                Selecionar documentos
              </Button>

              <Input
                name="file"
                type="file"
                ref={refFile}
                onChange={handleFileChange}
                multiple
                className="hidden"
              />
              {docs &&
                docs.map((item, key) => {
                  return (
                    <div
                      key={key}
                      className="flex items-center justify-between w-[270px] bg-foreground rounded-md px-2 py-1 mt-5"
                    >
                      <p className="font-open font-normal text-card-foreground text-xs truncate">
                        {item?.name}
                      </p>
                      <div
                        className="cursor-pointer hover:text-red-500 duration-200"
                        onClick={() => {
                          removeFile(docs.indexOf(item));
                        }}
                      >
                        <XCircle className="text-red-500 h-5 w-5 ml-4" />
                      </div>
                    </div>
                  );
                })}
              <p className="font-open font-normal text-black text-xs mt-3">
                Lembre-se de anexar cópias dos documentos atualizados e conferir
                todos os
                <br /> dados antes de confirmar.
              </p>
            </div>

            <span className="text-xs font-normal text-card-foreground">
              Lembre-se de anexar cópias dos documentos atualizados e conferir
              todos os dados antes de enviar.
            </span>
          </div>

          <Button className="w-full mt-8" type="submit">
            Enviar
          </Button>
        </div>
      </form>
    </FormProvider>
  );
};
