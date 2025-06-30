"use client";

import * as React from "react";
import { format } from "date-fns";
import { ptBR } from "date-fns/locale"; // Importe o locale pt-BR
import { Calendar as CalendarIcon } from "lucide-react";

import { cn } from "@/utils/utils";
import { Button } from "@/components/ui/button";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";

interface DatePickerDemoProps {
  value?: Date;
  onChange?: (date?: Date) => void;
  label: string;
}

export function DatePickerDemo({
  value,
  onChange,
  label,
}: DatePickerDemoProps) {
  return (
    <div className="w-full">
      <label className="text-gray-600 block text-sm font-medium">{label}</label>
      <Popover>
        <PopoverTrigger asChild className="text-muted-foreground">
          <Button
            variant={"outline"}
            className={cn(
              "w-full justify-start text-left font-normal",
              !value && "text-gray-400"
            )}
          >
            <CalendarIcon className="mr-2 h-4 w-4" />
            {value ? (
              format(value, "dd 'de' MMMM 'de' yyyy", { locale: ptBR }) // Formato brasileiro
            ) : (
              <span className="text-gray-400">Selecione uma data</span>
            )}
          </Button>
        </PopoverTrigger>
        <PopoverContent className="w-auto p-0">
          <Calendar
            mode="single"
            selected={value}
            onSelect={onChange}
            initialFocus
            locale={ptBR}
            className="text-gray-800"
            classNames={{
              caption_label: "text-gray-800",
            }}
          />
        </PopoverContent>
      </Popover>
    </div>
  );
}
