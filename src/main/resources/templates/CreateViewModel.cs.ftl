using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using static Luxshare.Api.Entities.Enums.CommonEnum;

namespace Luxshare.Api.ViewModels.Report.${entityName}
{
    /// <summary>
    /// 
    /// </summary>
    public class ${entityName}CreateViewModel
    {
    <#list columns as column>
        public ${column.propertyType} ${column.columnName} { get; set; }

    </#list>
        public IsDeleted IsDeleted { get; set; }

    }

}